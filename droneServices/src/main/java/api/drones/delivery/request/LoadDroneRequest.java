package api.drones.delivery.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoadDroneRequest {

	@NotBlank(message = "serialNumber is mandatory")
	private String serialNumber;
	@NotEmpty(message = "idMedications is mandatory")
	private Set<Long> idMedications;
	
}
