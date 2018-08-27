package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Route;
import domain.User;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {

	@Query("select a from Administrator a where a.userAccount.id=?1")
	Administrator findAdministratorByUserAccountId(int userAccountId);

	// The average and the standard deviation of routes per user.
	@Query("select avg(u.routes.size), stddev(u.routes.size) from User u")
	Collection<Double> dataRoutesPerUser();

	// The average and the standard deviation of hikes per route.
	@Query("select avg(r.hikes.size), stddev(r.hikes.size) from Route r")
	Collection<Double> dataHikesPerRoute();

	// The average and the standard deviation of the length of the routes.
	@Query("select avg(r.length), stddev(r.length) from Route r")
	Collection<Double> dataLengthOfRoutes();

	// The average and the standard deviation of the length of the hikes.
	@Query("select avg(h.length), stddev(h.length) from Hike h")
	Collection<Double> dataLengthOfHikes();

	// The outlier routes according to their lengths.
	// TODO
	@Query("select avg(h.length), stddev(h.length) from Hike h")
	Collection<Double> data();

	// The average number of chirps per user.
	@Query("select avg(u.chirps.size) from User u")
	Collection<Double> dataNumChirpsPerUser();

	// The users who have posted more than 75% the average number of chirps per
	// user.
	@Query("select u from User u where u.chirps.size>(select avg(u.chirps.size) from User u)*0.75")
	Collection<User> dataUserMore75Chirps();

	// The users who have posted less than 25% the average number of chirps per
	// user.
	@Query("select u from User u where u.chirps.size<(select avg(u.chirps.size) from User u)*0.25")
	Collection<User> dataUserLess25Chirps();

	// The average number of comments per route (including their hikes).
	@Query("select avg(r.comments.size+h.comments.size) from Route r join r.hikes h")
	Collection<Double> dataCommentPerRoute();

	// The average and the standard deviation of the number of inns managed per
	// innkeeper.
	@Query("select avg(i.inns.size), stddev(i.inns.size) from Innkeeper i")
	Collection<Double> dataInnsPerInnkeeper();

	// The average and the standard deviation of the number of users per day who
	// stay in the inns.
	@Query("select avg(i.registries.size), stddev(i.registries.size) from Inn i join i.registries r group by r.date")
	Collection<Double> dataNumUserPerDayInns();

	@Query("select r from Route r where r.length<=((select avg(f.length) from Route f)-(select stddev(h.length) from Route h)*3) or r.length>=((select avg(e.length) from Route e)+(select stddev(w.length) from Route w)*3)")
	Collection<Route> dataOutlierOfRoutes();

	// Acme-Santiago 2.0

	// The ratio of routes that have at least one advertisement versus the
	// routes that dont have any.
	@Query("select distinct(count(u.hike.route)) from Advertisement u  ") 
	Double dataRatioRoutedWithAvertisement1();
	
	@Query("select count(r)- (select distinct(count(u.hike.route)) from Advertisement u  )from Route r ") 
	Double dataRatioRoutedWithAvertisement2();
	// The ratio of advertisements that have taboo words.
	@Query("select cast(count(a) as float)/(select count(a) from Advertisement a) from Advertisement a where a.taboo=true") 
	Double dataRatioAdvertisementWithTaboo();

	// The ratio of Compostela requests that are awaiting a decision versus the
	// total number of requests.
	@Query("select (select count(u) from Compostela u where u.finallyDecision=false)* 1.0/count(c) from Compostela c where c.decision=true)") 
	Double dataRatioRequestWaiting();
	// The ratio of Compostela requests that are approved versus the total
	// number
	// of Compostela requests that are rejected.
	@Query("select (select count(u) from Compostela u where u.finallyDecision=true)* 1.0/count(c) from Compostela c where c.decision=true)") 
	Double dataRatioRequestApproved();
}
