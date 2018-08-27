package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Integer>{
	
	@Query("select a from Amenity a join a.inn i where i.id=?1")
	Collection<Amenity> findAmenitiesByInn(int innId);
}
