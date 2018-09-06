package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer>{

	@Query("select n from Notice n where n.discarded=false order by n.date desc")
	Collection<Notice> findNonDiscarded();

}
