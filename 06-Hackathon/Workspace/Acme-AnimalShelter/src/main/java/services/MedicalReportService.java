package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MedicalReportRepository;
import domain.MedicalReport;
import domain.Pet;


@Service
@Transactional
public class MedicalReportService {

	// Managed repository

	@Autowired
	private MedicalReportRepository medicalReportRepository;

	// Suporting services

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private PetService petService;
	
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
		this.administratorService.checkAuthority();
		Assert.isTrue(medicalReport.getVeterinary().isBan());
		Assert.notNull(medicalReport);
		Assert.isTrue(medicalReport.getId() != 0);
		Assert.isTrue(medicalReportRepository.exists(medicalReport.getId()));
		
		Pet pet = medicalReport.getPet();
		pet.setMedicalReport(null);
		this.petService.save(pet);
		
		medicalReportRepository.delete(medicalReport);
	}

	// Other business methods
	
	public Collection<MedicalReport> findMedicalReportsVeterinaryBanned(){
		Collection<MedicalReport> medicalReports = new ArrayList<MedicalReport>();
		medicalReports = this.medicalReportRepository.findMedicalReportsVeterinaryBanned();
		return medicalReports;
	}

}
