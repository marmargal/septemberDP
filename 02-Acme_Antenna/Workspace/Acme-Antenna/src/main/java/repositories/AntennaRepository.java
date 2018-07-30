package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Antenna;

@Repository
public interface AntennaRepository extends JpaRepository<Antenna, Integer>{

}
