package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Cambio;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Integer> {

}
