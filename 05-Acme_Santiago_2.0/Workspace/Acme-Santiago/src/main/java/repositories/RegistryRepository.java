package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Hike;
import domain.Inn;
import domain.Registry;
import domain.User;

@Repository
public interface RegistryRepository extends JpaRepository<Registry, Integer> {

	@Query("select r from Registry r where r.date = ?1 and r.inn = ?2 and r.user = ?3 and r.hike= ?4")
	Registry findRegistry(Date date, Inn inn, User user,Hike hike);
	
	@Query("select r from Registry r where r.user = ?1")
	Collection<Registry> findByUser(User user);
}
