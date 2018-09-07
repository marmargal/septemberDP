package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
<<<<<<< HEAD
import domain.Center;
=======
import domain.Client;
>>>>>>> master
import domain.Pet;
import domain.Report;

@Service
@Transactional
public class ApplicationService {

	// Managed repository

	@Autowired
	private ApplicationRepository applicationRepository;

	// Suporting services

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private PetService petService;

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ClientService clientService;

	// Constructors

	public ApplicationService() {
		super();
	}

	// Simple CRUD methods

	@SuppressWarnings("deprecation")
	public Application create(Pet pet) {
		Application res = new Application();

		
		Client client;
		client = clientService.findByPrincipal();
		res.setClient(client);
		res.setPet(pet);
		
		String ticker;
		Date date = new Date(System.currentTimeMillis() - 100);
		
		res.setCreateMoment(date);
		
		ticker = pet.getIdentifier() + date.getDay() + date.getMonth() + date.getYear();
		
		res.setClosed(false);
		
		res.setTicker(ticker);
		

		return res;

	}

	public Collection<Application> findAll() {
		Collection<Application> res;
		res = applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Application findOne(int applicationId) {
		Assert.isTrue(applicationId != 0);
		Application res;
		res = applicationRepository.findOne(applicationId);
		return res;
	}

	public Application save(Application application) {
		Application res;
		res = applicationRepository.save(application);
		return res;
	}

	public void delete(Application application) {
		this.administratorService.checkAuthority();
		// Assert.isTrue(application.getClosed() == false ||
		// application.getClient().isBan());
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);
		Assert.isTrue(applicationRepository.exists(application.getId()));

		// Borramos sus asociaciones
		Pet pet = application.getPet();
		pet.setApplication(null);
		this.petService.save(pet);

		Report report = application.getReport();
		if (report != null) {
			this.reportService.delete(report);
		}

		applicationRepository.delete(application);
	}

	// Other business methods

	public Collection<Application> findApplicationsPending() {
		Collection<Application> applications = new ArrayList<Application>();
		applications = this.applicationRepository.findApplicationsPending();
		return applications;
	}

	public Collection<Application> findApplicationsClientBan() {
		Collection<Application> applications = new ArrayList<Application>();
		applications = this.applicationRepository.findApplicationsClientBan();
		return applications;
	}

	public Collection<Application> findApplicationsPendingPerCentre(Center c) {
		Collection<Application> applications = new ArrayList<Application>();
		applications = this.applicationRepository
				.findApplicationsPendingPerCentre(c);
		return applications;
	}

}
