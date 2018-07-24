package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.ContactSection;

@Repository
public interface ContactSectionRepository extends JpaRepository<ContactSection, Integer>{
	
	@Query("select a from Application a join a.contactSection c where c.id = ?1")
	Application findApplicationbyContactSection(int id);

}
