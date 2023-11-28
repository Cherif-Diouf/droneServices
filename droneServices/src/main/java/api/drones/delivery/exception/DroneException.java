package api.drones.delivery.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DroneException extends Exception {

	public DroneException(String message) {
		super(message);
	}
	
	
}
