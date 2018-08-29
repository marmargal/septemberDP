package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer>{

}
