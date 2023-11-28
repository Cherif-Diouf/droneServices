package api.drones.delivery.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "DRONE")
public class Drone {
	
	public Drone() {
		super();
	}

	@Id
	@Size(min = 1, max = 100, message = "length of serialNumber should not be greater than 100")
	@Column(length = 100)
	private String serialNumber;
	
	@Max(value = 500,message = "weightLimit should not be greater than 500")
	@NotNull
	private double weightLimit;
	
	@Max(value = 100,message = "percentage should not be greater than 100")
	@NotNull
	private int battery;
	
	@Enumerated(EnumType.STRING)
	private DroneState state;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private DroneModel model;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "drone")
	private Set<Medication> medications;
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(double weightLimit) {
		this.weightLimit = weightLimit;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public DroneState getState() {
		return state;
	}

	public void setState(DroneState state) {
		this.state = state;
	}

	public DroneModel getModel() {
		return model;
	}

	public void setModel(DroneModel model) {
		this.model = model;
	}

	public Set<Medication> getMedications(){
		return medications;
	}
	
	public void setMedications(Set<Medication> medications){
		this.medications = medications;
	}
}

