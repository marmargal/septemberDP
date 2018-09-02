package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Query("select e from Employee e join e.userAccount ua where ua.id=?1")
	Employee findEmployeeByPrincipal(int uA);
	
	@Query("select e from Employee e join e.center c where c.id=?1")
	Collection<Employee> findByCenter(int centerId);
}
