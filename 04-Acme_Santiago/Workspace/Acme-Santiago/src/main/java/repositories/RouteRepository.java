package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

	@Query("select p from Route p join p.hikes h where (p.name like %?1% or p.description like %?1% or h.name like %?1%)")
	Collection<Route> searchRoute(String criteria);

}
