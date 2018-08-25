package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Advertisement;
import domain.Agent;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer>{
	
	@Query("select a from Advertisement a where a.hike = ?1")
	Collection<Advertisement> findAdvertisementByHike(int hikeId);
	
	@Query("select a from Agent a join a.advertisements s where s.id = ?1")
	Agent findAgentByAdvertisement(int advertisementId);
	
	@Query("select a from Advertisement a where a.taboo=true")
	Collection<Advertisement> findAdvertisementTaboo();

}
