package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Walk;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Integer>{

}
