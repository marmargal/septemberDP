package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.SocialSection;

@Repository
public interface SocialSectionRepository extends JpaRepository<SocialSection, Integer>{
	
	@Query("select a from Application a join a.socialSection s where s.id = ?1")
	Application findApplicationbySocialSection(int id);

}
