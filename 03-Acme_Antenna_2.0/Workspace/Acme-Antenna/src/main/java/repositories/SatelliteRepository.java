package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Satellite;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Integer>{

	@Query("select s from Satellite s where (s.name like %?1% or s.description like %?1%)")
	Collection<Satellite> searchSatellite(String criteria);
}
