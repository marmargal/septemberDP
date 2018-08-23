package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Compostela;

@Repository
public interface CompostelaRepository extends JpaRepository<Compostela, Integer>{

}
