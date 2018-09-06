package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Voluntary;

@Repository
public interface VoluntaryRepository extends JpaRepository<Voluntary, Integer>{

	@Query("select v from Voluntary v join v.userAccount ua where ua.id=?1")
	Voluntary findVoluntaryByPrincipal(int uA);
	
	@Query("select v from Employee e join e.stand s join s.voluntaries v where e.id=?1")
	Collection<Voluntary> findByStandEmployee(int employeeId);
}
