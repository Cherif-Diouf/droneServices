package api.drones.delivery.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.drones.delivery.entities.Drone;
import api.drones.delivery.entities.DroneState;


public interface DroneDao extends JpaRepository<Drone, String>{

	Drone findBySerialNumber(String serialNumber);
	
	List<Drone> findByStateAndBatteryGreaterThan(DroneState state, double battery);
}
