package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>{

	
}
