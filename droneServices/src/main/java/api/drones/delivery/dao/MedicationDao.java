package api.drones.delivery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import api.drones.delivery.entities.Medication;

public interface MedicationDao extends JpaRepository<Medication, Long>{

}
