package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Requirement;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

}
