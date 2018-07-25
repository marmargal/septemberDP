package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Visa;

@Repository
public interface VisaRepository extends JpaRepository<Visa,Integer>{

	@Query("select v from Visa v where (v.classes like %?1% or v.description like %?1%)")
	Collection<Visa> searchVisa(String criteria);
	
	@Query("select v from Visa v join v.category c where c.id = ?1")
	Collection<Visa> findVisasByCategories(int id);
	
	@Query("select v from Visa v join v.country c where c.id = ?1")
	Collection<Visa> findVisasByCountry(int id);
	
}
