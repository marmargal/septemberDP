package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Decision;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, Integer> {

}
