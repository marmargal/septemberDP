package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalSectionRepository;
import domain.Immigrant;
import domain.PersonalSection;

@Service
@Transactional
public class PersonalSectionService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private PersonalSectionRepository personalSectionRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ImmigrantService immigrantService;

	// Constructors -----------------------------------------------------------

	public PersonalSectionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public PersonalSection create() {
		final Immigrant immigrant = this.immigrantService.findByPrincipal();
		Assert.notNull(immigrant);
		PersonalSection res = new PersonalSection();
		final Collection<String> names = new ArrayList<String>();
		res.setNames(names);

		return res;
	}

	public Collection<PersonalSection> findAll() {
		Collection<PersonalSection> res;
		res = personalSectionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public PersonalSection findOne(int personalSectionId) {
		Assert.isTrue(personalSectionId != 0);
		PersonalSection res;
		res = personalSectionRepository.findOne(personalSectionId);
		Assert.notNull(res);
		return res;
	}

	public PersonalSection save(PersonalSection personalSection) {
		final Immigrant immigrant = this.immigrantService.findByPrincipal();
		Assert.notNull(immigrant);
		PersonalSection res;
		res = personalSectionRepository.save(personalSection);
		return res;
	}

	public void delete(PersonalSection personalSection) {
		Assert.notNull(personalSection);
		Assert.isTrue(personalSection.getId() != 0);
		Assert.isTrue(personalSectionRepository.exists(personalSection.getId()));
		personalSectionRepository.delete(personalSection);
	}
	
	// Other business methods -------------------------------------------------

}
