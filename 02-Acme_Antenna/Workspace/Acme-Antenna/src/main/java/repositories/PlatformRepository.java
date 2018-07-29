package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer>{

}
