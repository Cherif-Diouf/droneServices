package api.drones.delivery.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotFoundException extends Exception {

	public NotFoundException(String message) {
		super(message);
	}
	
	
}
