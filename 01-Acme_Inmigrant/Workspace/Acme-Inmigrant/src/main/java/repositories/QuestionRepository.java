package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@Query("select a from Officer o join o.decision d join d.application a where (d.accept=true and o.id=?1)")
	Application findApplicationSelfAsign(int officerId);
}
