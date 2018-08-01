package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select t from Category t where t.name='root'")
	Category findCategories();

	@Query("select case when (count(son.name) > 0) then true else false end from Category parent join parent.categories son where (son.name = ?1 and parent.id = ?2) or parent.name = ?1")
	boolean existsThisCategoryName(String name, int id);
}
