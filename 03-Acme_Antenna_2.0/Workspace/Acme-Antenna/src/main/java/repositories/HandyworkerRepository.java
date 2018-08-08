package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Handyworker;

@Repository
public interface HandyworkerRepository extends
JpaRepository<Handyworker, Integer>{

	@Query("select h from Handyworker h where h.userAccount.id=?1")
	Handyworker findHandyworkerByUserAccountId(int userAccountId);
	
}
