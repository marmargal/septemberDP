package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Officer;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, Integer>{

	@Query("select o from Officer o where o.userAccount.id=?1")
	Officer findOfficerByUserAccountId(int uA);
	
}
