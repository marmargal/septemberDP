package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer>{

	@Query("select a from Agent a where a.userAccount.id=?1")
	Agent findAgentByUserAccountId(int id);

}
