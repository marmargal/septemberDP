package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Stand;

@Repository
public interface StandRepository extends JpaRepository<Stand, Integer>{

}
