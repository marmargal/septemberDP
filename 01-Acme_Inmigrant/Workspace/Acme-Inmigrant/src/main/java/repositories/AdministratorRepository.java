package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {

	@Query("select a from Administrator a where a.userAccount.id=?1")
	Administrator findAdministratorByUserAccountId(int userAccountId);

	// The average, the minimum, the maximum, and the standard deviation of the
	// number of applications per user.
	@Query("select avg(i.applications.size),min(i.applications.size), max(i.applications.size), stddev(i.applications.size) from Immigrant i")
	Collection<Double> dataApplicationPerImmigrant();

	/*
	 * The average, the minimum, the maximum, and the standard deviation of the
	 * number of applications per officer.
	 */
	@Query("select avg(o.applications.size),min(o.applications.size), max(o.applications.size), stddev(o.applications.size) from Immigrant o")
	Collection<Double> dataApplicationsPerOfficer();

	/*
	 * The average, the minimum, the maximum, and the standard deviation of the
	 * price of the visas
	 */
	@Query("select avg(v.price),min(v.price), max(v.price), stddev(v.price) from Visa v")
	Collection<Double> dataPricePerVisa();

	/*
	 * The average, the minimum, the maximum, and the standard deviation of the
	 * number immigrants that are investigated per investigator.
	 */
	@Query("select avg(r.immigrant),min(i.immigrant), max(i.immigrant), stddev(i.immigrant) from Investigator i join i.reports r")
	Collection<Double> dataImmigrantsInvestigated();

	/*
	 * A chart with the average, the minimum, the maximum, and the standard
	 * de-viation of the time that elapses since an application is closed till
	 * an officer makes a decision on it.
	 */

	@Query("select avg(d.moment-d.application.closedMoment),min(d.moment-d.application.closedMoment),max(d.moment-d.application.closedMoment),"
			+ "stddev(d.moment-d.application.closedMoment) from Decision d")
	Collection<Date> dataTimeToMakeDecision();
	
	/*The minimum, the maximum, the average, and the standard deviation of the number of visas per category.*/
	@Query("select  avg(distinct(v.category)),min(distinct(v.category)), max(distinct(v.category)), stddev(distinct(v.category)) from Visa v")
	Collection<Double> dataNumberVisasPerCategory();
}
