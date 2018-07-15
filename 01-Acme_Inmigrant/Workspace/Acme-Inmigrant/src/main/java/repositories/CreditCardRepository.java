package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
	
	@Query("select c from CreditCard c where c.number = ?1 and c.CVV = ?2")
	CreditCard findSameCreditcard(String number, Integer cvv);

}
