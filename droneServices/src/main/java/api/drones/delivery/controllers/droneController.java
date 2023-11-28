package api.drones.delivery.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.drones.delivery.entities.Drone;
import api.drones.delivery.entities.Medication;
import api.drones.delivery.exception.DroneException;
import api.drones.delivery.exception.NotFoundException;
import api.drones.delivery.request.LoadDroneRequest;
import api.drones.delivery.response.BatteryLevelResponse;
import api.drones.delivery.services.DroneServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/drone")
public class droneController {
	
	@Autowired
	DroneServices droneServices;
	
	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Drone register(@Valid @RequestBody Drone drone) throws DroneException {
		return droneServices.register(drone);
	}
	
	@PutMapping("/loadDrone")
	public Drone loadDrone(@Valid @RequestBody LoadDroneRequest request) throws NotFoundException, DroneException {
		return droneServices.loadDrone(request);
	}
	
	@GetMapping("/listMedication")
	public Set<Medication> listMedication(@NotBlank @RequestParam("serialNumber") String serialNumber) throws NotFoundException {
		return droneServices.listMedications(serialNumber);
	}

	@GetMapping("/getAvailableDrones")
	public List<Drone> getAvailableDrone() {
		return droneServices.getAvailableDrone();
	}

	@GetMapping("/getBatteryLevel")
	public BatteryLevelResponse getBatteryLevel(@NotBlank @RequestParam("serialNumber") String serialNumber) throws NotFoundException {
		return droneServices.getBattery(serialNumber);
	}
}
