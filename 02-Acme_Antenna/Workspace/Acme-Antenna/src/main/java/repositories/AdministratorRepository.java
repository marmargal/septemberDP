package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Antenna;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{

	@Query("select a from Administrator a where a.userAccount.id=?1")
	Administrator findAdministratorByUserAccountId(int userAccountId);
	
	//The average and the standard deviation of the number of antennas per user
	@Query("select avg(u.antennas.size), stddev(u.antennas.size) from User u")
	Collection<Double> dataAntennasPerUser();
	
	//The average and the standard deviation of the quality of the antennas.
	@Query("select avg(a.quality), stddev(a.quality) from Antenna a")
	Collection<Double> dataQualityPerAntennas();
	
	//A chart with the number of antennas per model.
	
	//The top-3 antenna models in terms of popularity.
//	@Query("select a from Antenna a order by a.services.size desc")
//	Collection<Antenna> Top3AntennaPerpopularity();
	
	//The average and the standard deviation of the number of tutorials per user.
	@Query("select avg(u.tutorials.size), stddev(u.tutorials.size) from User u")
	Collection<Double> dataTutorialPerUser();
	
	//The average and the standard deviation of the number of comments per tutorial.
	@Query("select avg(u.comments.size), stddev(u.comments.size) from Tutorial u")
	Collection<Double> dataNumCommentPerTutorial();
	
	//The actors who have published a number of tutorials that is above the aver-age plus the standard deviation.
	
	//The average and the standard deviation of the number of replies per comment.
	@Query("select avg(u.replies.size), stddev(u.replies.size) from Comment u")
	Collection<Double> dataNumRepliesPerComment();
	//The average and the standard deviation of the length of the comments.
	//The average and the standard deviation of the number of pictures per tutorial.
	@Query("select avg(u.pictures.size), stddev(u.pictures.size) from Tutorial u")
	Collection<Double> dataNumPicturesPerTutorial();
	//The average and the standard deviation of the number of pictures per comment.
	@Query("select avg(u.pictures.size), stddev(u.pictures.size) from Comment u")
	Collection<Double> dataNumPicturesPerComment();
}
