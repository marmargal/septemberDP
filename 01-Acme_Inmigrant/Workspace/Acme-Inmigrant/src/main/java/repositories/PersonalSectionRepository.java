package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.PersonalSection;

@Repository
public interface PersonalSectionRepository extends JpaRepository<PersonalSection, Integer>{
	
	@Query("select a from Application a join a.personalSection p where p.id = ?1")
	Application findApplicationbyPersonalSection(int id);

}
