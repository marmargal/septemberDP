package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{

	@Query("select a from Administrator a join a.userAccount ua where ua.id=?1")
	Administrator findAdministratorByPrincipal(int uA);
}
