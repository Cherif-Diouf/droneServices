package api.drones.delivery.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DRONE_BATTERY_HISTORY")
@Data
public class DroneBatteryHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serialNumber;
	private Date dateWriting;
	private int battery;
	
	public DroneBatteryHistory(String serialNumber, int battery) {
		this.serialNumber = serialNumber;
		this.battery = battery;
		this.dateWriting = new Date();
	}
	
	
}
