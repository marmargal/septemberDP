package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.WorkSection;

@Repository
public interface WorkSectionRepository extends JpaRepository<WorkSection, Integer>{
	
	@Query("select a from Application a join a.workSection w where w.id = ?1")
	Application findApplicationbyWorkSection(int id);

}
