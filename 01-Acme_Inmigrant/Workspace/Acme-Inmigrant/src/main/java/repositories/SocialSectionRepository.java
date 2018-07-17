package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SocialSection;

@Repository
public interface SocialSectionRepository extends JpaRepository<SocialSection, Integer>{

}
