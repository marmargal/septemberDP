package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Integer>{

}
