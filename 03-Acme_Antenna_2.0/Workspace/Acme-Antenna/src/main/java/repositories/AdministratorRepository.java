package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Administrator;
import domain.Agent;
import domain.Antenna;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {

	@Query("select a from Administrator a where a.userAccount.id=?1")
	Administrator findAdministratorByUserAccountId(int userAccountId);

	// The average and the standard deviation of the number of antennas per user
	@Query("select avg(u.antennas.size), stddev(u.antennas.size) from User u")
	Collection<Double> dataAntennasPerUser();

	// The average and the standard deviation of the quality of the antennas.
	@Query("select avg(a.quality), stddev(a.quality) from Antenna a")
	Collection<Double> dataQualityPerAntennas();

	// A chart with the number of antennas per model.
	@Query("select count(a),a.model from Antenna a group by a.model")
	Collection<String> dataNumAntennasPerModel();

	// The top-3 antenna models in terms of popularity.
	@Query("select a from Antenna a group by a.model order by count(a.model) desc")
	Collection<Antenna> Top3AntennaPerpopularity();

	// The average and the standard deviation of the number of tutorials per
	// user.
	@Query("select avg(u.tutorials.size), stddev(u.tutorials.size) from User u")
	Collection<Double> dataTutorialPerUser();

	// The average and the standard deviation of the number of comments per
	// tutorial.
	@Query("select avg(u.comments.size), stddev(u.comments.size) from Tutorial u")
	Collection<Double> dataNumCommentPerTutorial();

	// The actors who have published a number of tutorials that is above the
	// aver-age plus the standard deviation.
	@Query("select a  from Actor a where a.tutorials.size>(select avg(b.tutorials.size)+stddev(b.tutorials.size) from Actor b)")
	Collection<Actor> actorHasPublished();

	// The average and the standard deviation of the number of replies per
	// comment.
	@Query("select avg(u.replies.size), stddev(u.replies.size) from Comment u")
	Collection<Double> dataNumRepliesPerComment();

	// The average and the standard deviation of the length of the comments.
	@Query("select avg(LENGTH(c.text)), stddev(LENGTH(c.text)) from Comment c")
	Collection<Double> dataNumLengthOfComments();

	// The average and the standard deviation of the number of pictures per
	// tutorial.
	@Query("select avg(u.pictures.size), stddev(u.pictures.size) from Tutorial u")
	Collection<Double> dataNumPicturesPerTutorial();

	// The average and the standard deviation of the number of pictures per
	// comment.
	@Query("select avg(u.pictures.size), stddev(u.pictures.size) from Comment u")
	Collection<Double> dataNumPicturesPerComment();

	//

	// The average and the standard deviation of the number of requests per
	// user.

	@Query("select avg(u.requests.size), stddev(u.requests.size) from User u")
	Collection<Double> dataNumRequestPerUser();

	// The average ratio of serviced requests per user.
	@Query(" select avg(u.requests.size), stddev(u.requests.size) from User u join u.requests r where r.finishMoment<=current_date")
	Collection<Double> dataServicedRequestPerUser();

	// The average ratio of serviced requests per handyworker.
	@Query("select avg(u.requests.size), stddev(u.requests.size) from Handyworker u")
	Collection<Double> dataNumRequestPerHandy();

	// The average number of banners per agent.
	@Query("select avg(u.banners.size) from Agent u")
	Collection<Double> dataNumBannerPerAgent();

	// The top-3 agents in terms of number of banners.
	@Query("select a from Agent a order by a.banners.size desc")
	Collection<Agent> topAgentNumberBanners();
}
