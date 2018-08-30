package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MedicalReportRepository;
import domain.MedicalReport;


@Service
@Transactional
public class MedicalReportService {

	// Managed repository

	@Autowired
	private MedicalReportRepository medicalReportRepository;

	// Suporting services

	// Constructors

	public MedicalReportService() {
		super();
	}

	// Simple CRUD methods

	public MedicalReport create() {
		MedicalReport res = new MedicalReport();
		
		return res;

	}

	public Collection<MedicalReport> findAll() {
		Collection<MedicalReport> res;
		res = medicalReportRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public MedicalReport findOne(int medicalReportId) {
		Assert.isTrue(medicalReportId != 0);
		MedicalReport res;
		res = medicalReportRepository.findOne(medicalReportId);
		Assert.notNull(res);
		return res;
	}

	public MedicalReport save(MedicalReport medicalReport) {
		MedicalReport res;
		res = medicalReportRepository.save(medicalReport);
		return res;
	}

	public void delete(MedicalReport medicalReport) {
		Assert.notNull(medicalReport);
		Assert.isTrue(medicalReport.getId() != 0);
		Assert.isTrue(medicalReportRepository.exists(medicalReport.getId()));
		medicalReportRepository.delete(medicalReport);
	}

	// Other business methods
	
	

}
