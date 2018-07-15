package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
