package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.EducationSection;

@Repository
public interface EducationSectionRepository extends JpaRepository<EducationSection, Integer>{
	
	@Query("select a from Application a join a.educationSection e where e.id = ?1")
	Application findApplicationbyEducationSection(int id);

}
