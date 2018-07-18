package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PersonalSection;

@Repository
public interface PersonalSectionRepository extends JpaRepository<PersonalSection, Integer>{

}
