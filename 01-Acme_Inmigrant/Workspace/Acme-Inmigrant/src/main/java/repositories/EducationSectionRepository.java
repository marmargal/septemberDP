package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.EducationSection;

@Repository
public interface EducationSectionRepository extends JpaRepository<EducationSection, Integer>{

}
