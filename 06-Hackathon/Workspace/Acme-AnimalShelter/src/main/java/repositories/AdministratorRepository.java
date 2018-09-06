package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Client;
import domain.Veterinary;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{

	@Query("select a from Administrator a join a.userAccount ua where ua.id=?1")
	Administrator findAdministratorByPrincipal(int uA);
	
	//ok
	//Media, máximo, mínimo y desviación típica de número de solicitudes por cliente.
	@Query("select avg(c.applications.size),min(c.applications.size), max(c.applications.size), stddev(c.applications.size) from Client c")
	Collection<Double> dataApplicationPerClient();
	
	//TODO
	//Media, máximo, mínimo y desviación típica de número de avisos por voluntario.
	@Query("select avg(distintc(count(n.voluntary))),min(distintc(count(n.voluntary))), max(distintc(count(n.voluntary))), stddev(distintc(count(n.voluntary))) from Notice n " )
	Collection<Double> dataNoticePerVoluntary();
	
	//TODO cambiar direccinalidad
	//Media, máximo, mínimo y desviación típica de número de solicitudes asignadas por empleado.
	@Query("select avg(c.applications.size),min(c.applications.size), max(c.applications.size), stddev(c.applications.size) from Client c")
	Collection<Double> dataApplicationPerEmployee();
	
	//ok
	//Media, máximo, mínimo y desviación típica de número de reportes por empleado
	@Query("select avg(c.reports.size),min(c.reports.size), max(c.reports.size), stddev(c.reports.size) from Employee c")
	Collection<Double> dataReportPorEmpleado();
	
	
	//Media, máximo, mínimo y desviación típica informes por veterinario.
	//TODO mirar distinct
	@Query("select avg(count(c)),min(count(c)), max(count(c)), stddev(count(c))  from MedicalReport c group by c.veterinary")
	Collection<Double> dataMedicalReportPerVeterinary();
	
	//Media, máximo, mínimo y desviación típica de número de solicitudes por cliente en la última semana.
	@Query("select avg(c.applications.size),min(c.applications.size), max(c.applications.size), stddev(c.applications.size) from Client c where c.fecha>=fecha")
	Collection<Double> dataApplicationPerClientLastWeek(Date fecha);
	
	
	//Media, máximo, mínimo y desviación típica de número de informes por veterinario en la última semana.
	@Query("select avg(c.applications.size),min(c.applications.size), max(c.applications.size), stddev(c.applications.size) from Client c")
	Collection<Double> dataMedicalReportPerVeterinaryLastWeek();
	
	
	//Media, máximo, mínimo y desviación típica de número de reportes por empleado en la última semana.
	
	@Query("select avg(c.applications.size),min(c.applications.size), max(c.applications.size), stddev(c.applications.size) from Client c")
	Collection<Double> dataReportPerEmployeeLastWeek();
	
	
	//----------------------------------------------------------------------
	
	//Los clientes con más solicitudes.
	@Query("select c from Client c where c.applications>2")
	Collection<Client> clientsWithMoreApplications();
	
	//Los veterinarios con mas y con menos informes.
	
	@Query("select c from Veterinary c where c.reports>2")
	Collection<Veterinary> veterinariesWithMoreReports();
	
	//Los empleados con más y con menos reportes
	
	@Query("select c from Emplyee c where c.reports>2 ")
	Collection<Veterinary> employeesWithMoreReports();
	
	@Query("select c from Emplyee c  where c.reports<2")
	Collection<Veterinary> employeesWithLessReports();
	
	//Los veterinarios que están en más del 50% de la media de informes.
	@Query("select u from Veterinary u where u.chirps.size>(select avg(u.chirps.size) from User u)*0.75")
	Collection<Veterinary> dataUserMore75Chirps();

}
