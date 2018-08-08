package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer>{

	@Query("select p from Platform p where (p.name like %?1% or p.description like %?1%)")
	Collection<Platform> searchPlatform(String criteria);
	
	@Query("select s.platform from User u join u.subscriptions s where s.endDate > ?2 and u.id=?1")
	Collection<Platform> findPlatformByUser(int userId, Date date);
	
}
