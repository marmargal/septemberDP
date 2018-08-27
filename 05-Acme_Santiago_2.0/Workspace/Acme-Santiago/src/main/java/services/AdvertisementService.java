package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdvertisementRepository;
import domain.Advertisement;

@Service
@Transactional
public class AdvertisementService {

	// Managed repository
	
	@Autowired
	private AdvertisementRepository advertisementRepository;
	
	// Supporting services
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	// Constructors
	
	public AdvertisementService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Advertisement create(){
		Advertisement res = new Advertisement();
		return res;
	}
	
	public Collection<Advertisement> findAll(){
		Collection<Advertisement> res;
		res = this.advertisementRepository.findAll();
		return res;
	}
	
	public Advertisement findOne(final int id){
		Assert.isTrue(id!=0);
		Advertisement res;
		res = this.advertisementRepository.findOne(id);
		return res;
	}
	
	public Advertisement save(final Advertisement advertisement){
		Assert.notNull(advertisement);
		Advertisement res;
		Collection<String> tabooWords = new ArrayList<String>();
		tabooWords = configurationService.findTabooWords();
		for (String s : tabooWords) {
			if (advertisement.getTitle().toLowerCase().contains(s.toLowerCase())) {
				advertisement.setTaboo(true);
			}
		}
		res = this.advertisementRepository.save(advertisement);
		return res;
	}
	
	public void delete(Advertisement advertisement){
		Assert.notNull(advertisement);
		Assert.isTrue(advertisement.getId()!=0);
		Assert.isTrue(this.advertisementRepository.exists(advertisement.getId()));
		domain.Agent agent = this.findAgentByAdvertisement(advertisement.getId());
		Collection<Advertisement> advertisements = new ArrayList<>();
		advertisements = agent.getAdvertisements();
		advertisements.remove(advertisement);
		this.administratorService.checkAuthority();
		this.advertisementRepository.delete(advertisement);
	}
	
	// Other business methods
	
	public Collection<Advertisement> findAdvertisementByHike(int hikeId) {
		Collection<Advertisement> res;
		res = this.advertisementRepository.findAdvertisementByHike(hikeId);
		return res;
	}
	
	public domain.Agent findAgentByAdvertisement(int advertisementId) {
		domain.Agent res;
		res = this.advertisementRepository.findAgentByAdvertisement(advertisementId);
		return res;
	}
	
	public Collection<Advertisement> findAdvertisementTaboo() {
		return advertisementRepository.findAdvertisementTaboo();
	}
}
