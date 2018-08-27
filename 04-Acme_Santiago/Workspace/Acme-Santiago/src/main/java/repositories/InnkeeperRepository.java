package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Innkeeper;

@Repository
public interface InnkeeperRepository extends JpaRepository<Innkeeper, Integer> {

	@Query("select a from Innkeeper a where a.userAccount.id=?1")
	Innkeeper findInnkeeperByUserAccountId(int userAccountId);

}
