package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Inkeeper;

@Repository
public interface InkeeperRepository extends JpaRepository<Inkeeper, Integer> {

	@Query("select a from Inkeeper a where a.userAccount.id=?1")
	Inkeeper findInkeeperByUserAccountId(int userAccountId);

}
