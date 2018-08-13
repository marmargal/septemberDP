package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CreditCard;
import domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {


	@Query("select r from Request r where (r.user.id = ?1 and r.finishMoment is not null)")
	Collection<Request> alreadyServicedRequest(int userId);

	@Query("select r from Request r where (r.user.id = ?1 and r.finishMoment is null)")
	Collection<Request> notYetServicedRequest(int userId);

	@Query("select r.creditCard from User u join u.requests r where u.id=?1 order by r.moment DESC")
	Collection<CreditCard> findAllCreditCard(int id);

}
