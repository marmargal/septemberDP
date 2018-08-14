package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Hike;

@Repository
public interface HikeRepository extends JpaRepository<Hike, Integer>{

	
}
