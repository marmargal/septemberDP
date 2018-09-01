package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MedicalReport;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Integer>{
	
	@Query("select mr from MedicalReport mr join mr.veterinary v where v.ban=true")
	Collection<MedicalReport> findMedicalReportsVeterinaryBanned();
}
