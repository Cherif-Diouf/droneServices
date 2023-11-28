package api.drones.delivery.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import api.drones.delivery.dao.DroneBatteryHistoryDao;
import api.drones.delivery.dao.DroneDao;
import api.drones.delivery.dao.MedicationDao;
import api.drones.delivery.entities.Drone;
import api.drones.delivery.entities.DroneBatteryHistory;
import api.drones.delivery.entities.DroneState;
import api.drones.delivery.entities.Medication;
import api.drones.delivery.exception.DroneException;
import api.drones.delivery.exception.NotFoundException;
import api.drones.delivery.request.LoadDroneRequest;
import api.drones.delivery.response.BatteryLevelResponse;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DroneServices {
	
	@Autowired
	DroneDao droneDao;
	
	@Autowired
	MedicationDao medicationDao;
	
	@Autowired
	DroneBatteryHistoryDao droneBatteryHistoryDao;

	public Drone register(Drone drone) throws DroneException {
		Drone dr = droneDao.findBySerialNumber(drone.getSerialNumber());
		if(dr != null)
			throw new DroneException("Drone already exists");

		drone.setState(DroneState.IDLE);
		drone.setMedications(null);
		droneDao.save(drone);
		return drone; 
	}
	
	public Drone loadDrone(LoadDroneRequest req) throws NotFoundException, DroneException {
		Set<Medication> medications = new HashSet<>();
		Drone drone = droneDao.findBySerialNumber(req.getSerialNumber());
		if(drone == null)
			throw new NotFoundException("Drone not found");
		
		if(!drone.getState().equals(DroneState.IDLE))
			throw new DroneException("Drone is not available for loading");
		
		if(drone.getBattery()< 25)
			throw new DroneException("Drone battery is low");
		
		if(req.getIdMedications().size() == 0)
			throw new NotFoundException("Medications not defined");
		req.getIdMedications().stream().forEach(id -> {
			Optional<Medication> med = medicationDao.findById(id);
			if(med.isPresent() && med.get().getDrone() == null) { 
				medications.add(med.get());
			}
		});
		Set<Medication> medicationsAvailable = medications.stream().filter(m -> m.getDrone() == null).collect(Collectors.toSet());
		
		if(medicationsAvailable.size() == 0)
			throw new NotFoundException("The list of available medication is empty");
		
		for (Medication medication : medicationsAvailable) {
			medication.setDrone(drone);
			medicationDao.save(medication);
		}
		
		double totalWeight = medicationsAvailable.stream().map(m -> m.getWeight()).collect(Collectors.summingDouble(Double::doubleValue));
		if(totalWeight > drone.getWeightLimit())
			throw new DroneException("Authorized weight is exceeded by "+(totalWeight - drone.getWeightLimit()));
		
		drone.setMedications(medicationsAvailable);
		drone.setState(DroneState.LOADED);
		
		return droneDao.save(drone);
		
	}

	public Set<Medication> listMedications(String serialNumber) throws NotFoundException {
		Drone drone = droneDao.findBySerialNumber(serialNumber);
		if(drone == null)
			throw new NotFoundException("Drone not found");
		return drone.getMedications();
	}
	
	public List<Drone> getAvailableDrone() {
		List<Drone> drones = droneDao.findByStateAndBatteryGreaterThan(DroneState.IDLE, 25d);
		return drones;
	}
	
	public BatteryLevelResponse getBattery(String serialNumber) throws NotFoundException {
		Drone drone = droneDao.findBySerialNumber(serialNumber);
		if(drone == null)
			throw new NotFoundException("Drone not found");
		
		return new BatteryLevelResponse(drone.getSerialNumber(), drone.getBattery());
	}
	
	@Scheduled(fixedRate = 1,timeUnit = TimeUnit.MINUTES)
	void batteryChecking() {
		log.info("******************* BATTERY CHECKING BATCH *******************");
		List<Drone> lDrones = droneDao.findAll();
		lDrones.forEach(dr -> {
			DroneBatteryHistory dbh = new DroneBatteryHistory(dr.getSerialNumber(), dr.getBattery());
			droneBatteryHistoryDao.save(dbh);
		});
	}
		
}
