package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

}
