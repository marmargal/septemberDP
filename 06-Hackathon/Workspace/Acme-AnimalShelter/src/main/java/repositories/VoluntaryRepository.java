package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Voluntary;

@Repository
public interface VoluntaryRepository extends JpaRepository<Voluntary, Integer>{

	@Query("select v from Voluntary v join v.userAccount ua where ua.id=?1")
	Voluntary findVoluntaryByPrincipal(int uA);
}
