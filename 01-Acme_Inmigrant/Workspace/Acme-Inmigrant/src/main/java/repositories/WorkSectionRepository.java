package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.WorkSection;

@Repository
public interface WorkSectionRepository extends JpaRepository<WorkSection, Integer>{

}
