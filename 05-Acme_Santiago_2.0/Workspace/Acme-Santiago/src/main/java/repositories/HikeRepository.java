package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Hike;

@Repository
public interface HikeRepository extends JpaRepository<Hike, Integer> {

	@Query("select h from Hike h where h.route.id=?1")
	Collection<Hike> findHikeByRoute(int id);

	@Query("select a.hike from Advertisement a where a.id = ?1")
	Collection<Hike> findHikeByAdvertisement(int id);

	@Query("select h from Hike h where h.destinationCity = ?1")
	Collection<Hike> findByCity(String city);

}
