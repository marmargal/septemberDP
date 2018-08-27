package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Walk;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Integer>{

	@Query("select w from Walk w join w.route r where r.user.id = ?1")
	Collection<Walk> findWalkByUser(int id);
}
