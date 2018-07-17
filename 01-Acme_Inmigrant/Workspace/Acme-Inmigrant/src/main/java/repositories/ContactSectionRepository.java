package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ContactSection;

@Repository
public interface ContactSectionRepository extends JpaRepository<ContactSection, Integer>{

}
