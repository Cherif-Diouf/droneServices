package api.drones.delivery.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BatteryLevelResponse {

	private String serialNumber;
	private int battery;
	
	public BatteryLevelResponse(String serialNumber, int battery) {
		this.serialNumber = serialNumber;
		this.battery = battery;
	}
}
