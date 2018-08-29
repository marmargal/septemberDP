package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	@Query("select c from Client c join c.userAccount ua where ua.id=?1")
	Client findClientByPrincipal(int uA);
}
