package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Client;
import domain.Employee;
import domain.Veterinary;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{

	@Query("select a from Administrator a join a.userAccount ua where ua.id=?1")
	Administrator findAdministratorByPrincipal(int uA);
	
	//ok
	//Media, m�ximo, m�nimo y desviaci�n t�pica de n�mero de solicitudes por cliente.
	@Query("select avg(c.applications.size),min(c.applications.size), max(c.applications.size), stddev(c.applications.size) from Client c")
	Collection<Double> dataApplicationPerClient();
	
	//ok
	//Media, m�ximo, m�nimo y desviaci�n t�pica de n�mero de avisos por voluntario.
	@Query("select avg(v.notice.size),min(v.notice.size), max(v.notice.size), stddev(v.notice.size) from Voluntary v " )
	Collection<Double> dataNoticePerVoluntary();
	
	//ok
	//Media, m�ximo, m�nimo y desviaci�n t�pica de n�mero de solicitudes asignadas por empleado.
	@Query("select avg(e.reports.size),min(e.reports.size), max(e.reports.size), stddev(e.reports.size) from Employee e")
	Collection<Double> dataApplicationPerEmployee();
	
	//ok
	//Media, m�ximo, m�nimo y desviaci�n t�pica de n�mero de reportes por empleado
	@Query("select avg(c.reports.size),min(c.reports.size), max(c.reports.size), stddev(c.reports.size) from Employee c")
	Collection<Double> dataReportPorEmpleado();
	
	
	//Media, m�ximo, m�nimo y desviaci�n t�pica informes por veterinario.
	//ok
	@Query("select avg(v.medicalReport.size),min(v.medicalReport.size), max(v.medicalReport.size), stddev(v.medicalReport.size)  from Veterinary v ")
	Collection<Double> dataMedicalReportPerVeterinary();
	
	//------------------------------------------------
	
	//Media, m�ximo, m�nimo y desviaci�n t�pica de n�mero de solicitudes por cliente en la �ltima semana.
	@Query("select avg(c.applications.size),min(c.applications.size), max(c.applications.size), stddev(c.applications.size) from Client c join c.applications x where x.createMoment>=?1")
	Collection<Double> dataApplicationPerClientLastWeek(Date fecha);
	
	
	//Media, m�ximo, m�nimo y desviaci�n t�pica de n�mero de informes por veterinario en la �ltima semana.
	@Query("select avg(v.medicalReport.size),min(v.medicalReport.size), max(v.medicalReport.size), stddev(v.medicalReport.size)  from Veterinary v join v.medicalReport x where x.date>=?1")
	Collection<Double> dataMedicalReportPerVeterinaryLastWeek(Date fecha);
	
	
	//Media, m�ximo, m�nimo y desviaci�n t�pica de n�mero de reportes por empleado en la �ltima semana.
	
	@Query("select avg(c.reports.size),min(c.reports.size), max(c.reports.size), stddev(c.reports.size) from Employee c join c.reports x where x.makeMoment>=?1")
	Collection<Double> dataReportPerEmployeeLastWeek(Date fecha);
	
	
	//----------------------------------------------------------------------
	
	//Los clientes con m�s solicitudes.
	@Query("select c from Client c where c.applications.size>=2")
	Collection<Client> clientsWithMoreApplications();
	
	//Los veterinarios con mas y con menos informes.
	
	@Query("select c from Veterinary c where c.medicalReport.size>=2")
	Collection<Veterinary> veterinariesWithMoreMedicalReport();
	
	//Los empleados con m�s y con menos reportes
	
	@Query("select c from Employee c where c.reports.size>=2 ")
	Collection<Employee> employeesWithMoreReports();
	
	@Query("select c from Employee c  where c.reports.size<2")
	Collection<Employee> employeesWithLessReports();
	
	//Los veterinarios que est�n en m�s del 50% de la media de informes.
	@Query("select u from Veterinary u where u.medicalReport.size>(select avg(u.medicalReport.size) from Veterinary u)*0.5")
	Collection<Veterinary> dataVeterinaryMore50MedicalReport();

}
