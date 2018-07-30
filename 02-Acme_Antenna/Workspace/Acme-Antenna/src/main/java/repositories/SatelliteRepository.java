package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Satellite;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Integer>{

}
