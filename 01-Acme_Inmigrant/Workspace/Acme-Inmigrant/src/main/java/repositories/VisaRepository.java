package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Visa;

@Repository
public interface VisaRepository extends JpaRepository<Visa,Integer>{

}
