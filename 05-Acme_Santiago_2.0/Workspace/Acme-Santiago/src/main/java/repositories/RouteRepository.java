<<<<<<< HEAD
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Hike;
import domain.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

	@Query("select p from Route p where p.name like %?1% or p.description like %?1%")
	Collection<Route> searchRoute(String criteria);

	@Query("select p from Route p join p.hikes h where h.name like %?1%")
	Collection<Route> searchRoute2(String hike);
	@Query("select p from Route p where p.length<=?1 and p.length>=?2")
	Collection<Route> lengthRoute(double max, double min);

	@Query("select p from Route p where p.hikes.size<=?1 and p.hikes.size>=?2")
	Collection<Route> numHikesRoute(int max, int min);
	
	@Query("select h from Hike h where h.route = null")
	Collection<Hike> hikesWithoutRoute();

}
=======
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Hike;
import domain.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

	@Query("select p from Route p join p.hikes h where (p.name like %?1% or p.description like %?1% or h.name like %?1%)")
	Collection<Route> searchRoute(String criteria);

	@Query("select p from Route p where p.length<=?1 and p.length>=?2")
	Collection<Route> lengthRoute(double max, double min);

	@Query("select p from Route p where p.hikes.size<=?1 and p.hikes.size>=?2")
	Collection<Route> numHikesRoute(int max, int min);
	
	@Query("select h from Hike h where h.route = null")
	Collection<Hike> hikesWithoutRoute();

}
>>>>>>> 254c2e47546558cea9c3c67d06e9dc79abfb610c
