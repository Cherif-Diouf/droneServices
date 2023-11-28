package api.drones.delivery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import api.drones.delivery.entities.DroneBatteryHistory;

public interface DroneBatteryHistoryDao extends JpaRepository<DroneBatteryHistory, Long>{

}
