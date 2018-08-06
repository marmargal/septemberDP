package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Country;
import domain.Visa;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	  @Query("select c from Country c join c.law l where l.id =?1")
	    Collection<Country> findCountryByLawId(int lawId);
	  @Query("select v from Visa v where v.country.id=?1")
	    Collection<Visa> visaCheck(int countryId);
}
