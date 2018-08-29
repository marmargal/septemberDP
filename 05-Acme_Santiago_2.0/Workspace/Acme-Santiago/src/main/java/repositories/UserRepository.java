package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Compostela;
import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.userAccount.id=?1")
	User findUserByUserAccountId(int userAccountId);
	
	@Query("select u from User u where ?1 MEMBER OF u.compostelas")
	User findUserByCompostela(Compostela compostela);
}
