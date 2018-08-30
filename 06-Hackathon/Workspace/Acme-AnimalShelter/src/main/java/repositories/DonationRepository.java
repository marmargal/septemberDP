package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer>{

}
