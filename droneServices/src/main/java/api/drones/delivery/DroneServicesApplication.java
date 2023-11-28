package api.drones.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import api.drones.delivery.dao.MedicationDao;
import api.drones.delivery.entities.Medication;

@SpringBootApplication
@EnableScheduling
public class DroneServicesApplication implements CommandLineRunner{

	@Autowired
	MedicationDao medicationDao;
	
	public static void main(String[] args) {
		SpringApplication.run(DroneServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		medicationDao.save(new Medication("Medicament_1", 177d, "MDCM_1", "/opt/image/med1.jpeg"));
		medicationDao.save(new Medication("Medicament_2", 143d, "MDCM_2", "/opt/image/med2.jpeg"));
		medicationDao.save(new Medication("Medicament_3", 154d, "MDCM_3", "/opt/image/med3.jpeg"));
		medicationDao.save(new Medication("Medicament_4", 207d, "MDCM_4", "/opt/image/med4.jpeg"));
		medicationDao.save(new Medication("Medicament_5", 209d, "MDCM_5", "/opt/image/med5.jpeg"));
		medicationDao.save(new Medication("Medicament_6", 200d, "MDCM_6", "/opt/image/med6.jpeg"));
		medicationDao.save(new Medication("Medicament_7", 90d, "MDCM_7", "/opt/image/med7.jpeg"));
		medicationDao.save(new Medication("Medicament_8", 88d, "MDCM_8", "/opt/image/med8.jpeg"));
		medicationDao.save(new Medication("Medicament_9", 45d, "MDCM_9", "/opt/image/med9.jpeg"));
		medicationDao.save(new Medication("Medicament_10", 76d, "MDCM_10", "/opt/image/med10.jpeg"));
		medicationDao.save(new Medication("Medicament_11", 34d, "MDCM_11", "/opt/image/med11.jpeg"));
		medicationDao.save(new Medication("Medicament_12", 188d, "MDCM_12", "/opt/image/med12.jpeg"));
		medicationDao.save(new Medication("Medicament_13", 400d, "MDCM_13", "/opt/image/med13.jpeg"));
		
	}

}
