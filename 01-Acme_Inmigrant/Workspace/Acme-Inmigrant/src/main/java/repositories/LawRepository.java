package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Law;

@Repository
public interface LawRepository extends JpaRepository<Law,Integer>{

}
