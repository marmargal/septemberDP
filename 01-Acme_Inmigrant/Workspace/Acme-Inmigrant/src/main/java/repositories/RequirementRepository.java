package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Requirement;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

	@Query("select r from Visa v join v.country c join c.law l join l.requirement r where v.id= ?1")
	Collection<Requirement> findRequirementByVisaId(int visaId);

}
