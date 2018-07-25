package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Immigrant;
import domain.Investigator;


@Repository
public interface InvestigatorRepository extends JpaRepository<Investigator, Integer>{

	@Query("select i from Investigator i where i.userAccount.id=?1")
	Investigator findInvestigatorByUserAccountId(int uA);
	
	@Query("select a from Application a join a.officer o where o.id=?1")
	Collection<Application> findApplicationByOfficer(int uA);

	@Query("select i from Immigrant i join i.applications a where a.id=?1")
	Immigrant findImmigrantByApplication(int uA);
	
	@Query("select imm.investigator from Immigrant imm where imm.id=?1")
	Investigator findInvestigatorByImmigrant(int uA);
	
}
