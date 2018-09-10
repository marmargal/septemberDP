package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContactSectionRepository;
import domain.Application;
import domain.ContactSection;
import domain.Immigrant;

@Service
@Transactional
public class ContactSectionService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private ContactSectionRepository contactSectionRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ImmigrantService immigrantService;

	// Constructors -----------------------------------------------------------

	public ContactSectionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public ContactSection create() {
		this.immigrantService.checkAuthority();
		final Immigrant immigrant = this.immigrantService.findByPrincipal();
		Assert.notNull(immigrant);
		ContactSection res = new ContactSection();
		return res;
	}

	public Collection<ContactSection> findAll() {
		Collection<ContactSection> res;
		res = contactSectionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public ContactSection findOne(int contactSectionId) {
		Assert.isTrue(contactSectionId != 0);
		ContactSection res;
		res = contactSectionRepository.findOne(contactSectionId);
		Assert.notNull(res);
		return res;
	}

	public ContactSection save(ContactSection contactSection) {
		this.immigrantService.checkAuthority();
		Assert.isTrue(immigrantService.findByPrincipal() == contactSection.getApplication().getImmigrant());
		ContactSection res;
		
		if(contactSection.getId() != 0){
			res = contactSectionRepository.save(contactSection);
		}else{
			Application a = contactSection.getApplication();
			List<ContactSection> contactSections = new ArrayList<ContactSection>();
			contactSections = a.getContactSection();
			contactSections.add(contactSection);
			a.setContactSection(contactSections);
			
			res = contactSectionRepository.save(contactSection);
		}
		
		return res;
	}

	public void delete(ContactSection contactSection) {
		Assert.notNull(contactSection);
		Assert.isTrue(contactSection.getId() != 0);
		Assert.isTrue(contactSectionRepository.exists(contactSection.getId()));
		
		Application application = this.findApplicationbyContactSection(contactSection.getId());
		List<ContactSection> cs = application.getContactSection();
		cs.remove(contactSection);
		application.setContactSection(cs);
		
		contactSectionRepository.delete(contactSection);
	}
	
	// Other business methods -------------------------------------------------
	
	public Application findApplicationbyContactSection(Integer id) {
		Application res = new Application();
		
		res = contactSectionRepository.findApplicationbyContactSection(id);
		
		return res;
	}
	
	public void flush() {
		this.contactSectionRepository.flush();
	}

}
