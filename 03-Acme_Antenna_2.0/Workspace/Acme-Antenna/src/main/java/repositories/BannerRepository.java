package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

}
