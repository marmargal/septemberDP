package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DonationRepository;
import domain.Client;
import domain.Donation;
import domain.Event;
import domain.Voluntary;


@Service
@Transactional
public class DonationService {

	// Managed repository

	@Autowired
	private DonationRepository donationRepository;

	// Suporting services
	
	@Autowired
	private VoluntaryService voluntaryService;
	
	@Autowired
	private ClientService clientService;

	// Constructors

	public DonationService() {
		super();
	}

	// Simple CRUD methods

	public Donation create(Event event) {
		Donation res = new Donation();
		
		try {
			Voluntary voluntary = voluntaryService.findByPrincipal();
			res.setVoluntary(voluntary);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			Client client = clientService.findByPrincipal();
			res.setClient(client);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		res.setEvent(event);
		
		return res;

	}

	public Collection<Donation> findAll() {
		Collection<Donation> res;
		res = donationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Donation findOne(int donationId) {
		Assert.isTrue(donationId != 0);
		Donation res;
		res = donationRepository.findOne(donationId);
		Assert.notNull(res);
		return res;
	}

	public Donation save(Donation donation) {
		Donation res;
		res = donationRepository.save(donation);
		return res;
	}

	public void delete(Donation donation) {
		Assert.notNull(donation);
		Assert.isTrue(donation.getId() != 0);
		Assert.isTrue(donationRepository.exists(donation.getId()));
		donationRepository.delete(donation);
	}

	// Other business methods
	
	
	public void flush() {
		this.donationRepository.flush();

	}
}
