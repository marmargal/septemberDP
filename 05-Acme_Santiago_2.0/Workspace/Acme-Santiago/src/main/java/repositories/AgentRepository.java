package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer>{

}
