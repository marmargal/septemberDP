package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Antenna;

@Repository
public interface AntennaRepository extends JpaRepository<Antenna, Integer>{
	
	@Query("select u.antennas from User u where u.id=?1")
	Collection<Antenna> findAntennasByUser(int userId);
	
}
