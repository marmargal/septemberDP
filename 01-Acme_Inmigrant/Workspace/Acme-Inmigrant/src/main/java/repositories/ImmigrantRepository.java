package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Immigrant;

@Repository
public interface ImmigrantRepository extends JpaRepository<Immigrant, Integer>{

	@Query("select i from Immigrant i where i.userAccount.id=?1")
	Immigrant findImmigrantByUserAccountId(int uA);
	
}
