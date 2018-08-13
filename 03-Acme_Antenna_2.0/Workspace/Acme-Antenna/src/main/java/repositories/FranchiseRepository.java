package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Franchise;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
	
	@Query("select max(id) from Franchise")
	Integer resId();

}