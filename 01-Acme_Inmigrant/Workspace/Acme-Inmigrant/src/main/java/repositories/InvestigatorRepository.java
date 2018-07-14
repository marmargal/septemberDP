package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Investigator;


@Repository
public interface InvestigatorRepository extends JpaRepository<Investigator, Integer>{

	@Query("select i from Investigator i where i.userAccount.id=?1")
	Investigator findInvestigatorByUserAccountId(int uA);
}
