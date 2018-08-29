package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Boss;

@Repository
public interface BossRepository extends JpaRepository<Boss, Integer>{

	@Query("select b from Boss b join b.userAccount ua where ua.id=?1")
	Boss findBossByPrincipal(int uA);
}
