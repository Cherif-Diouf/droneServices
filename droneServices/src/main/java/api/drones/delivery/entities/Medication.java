package api.drones.delivery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "MEDICATION")
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "")
	@NotNull
	private String name;
	
	@NotNull
	private double weight;
	
	@Pattern(regexp = "^[A-Z0-9_]+$", message = "")
	@NotNull
	private String code;
	
	private String image;
	
	@JsonIgnore
	@JoinColumn(name = "serialNumberDrone")
	@ManyToOne(cascade = CascadeType.ALL)
	private Drone drone;

	public Medication(String name, double weight, String code, String image) {
		super();
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
	}
	
	public Medication() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}
	
	
	
}
