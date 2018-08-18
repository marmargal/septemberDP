package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Inn;

@Repository
public interface InnRepository extends JpaRepository<Inn, Integer>{

	
}
