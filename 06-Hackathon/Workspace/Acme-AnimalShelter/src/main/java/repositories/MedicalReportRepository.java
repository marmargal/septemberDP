package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.MedicalReport;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Integer>{

}
