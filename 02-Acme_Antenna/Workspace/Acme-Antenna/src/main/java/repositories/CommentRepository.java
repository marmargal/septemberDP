package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Antenna;
import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{

}
