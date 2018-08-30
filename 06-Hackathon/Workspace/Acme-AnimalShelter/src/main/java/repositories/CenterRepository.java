package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer>{

}
