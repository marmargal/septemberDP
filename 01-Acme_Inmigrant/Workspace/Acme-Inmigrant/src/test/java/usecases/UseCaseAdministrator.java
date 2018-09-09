package usecases;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;
import security.UserAccount;
import services.CategoryService;
import services.CountryService;
import services.InvestigatorService;
import services.OfficerService;
import utilities.AbstractTest;
import domain.Category;
import domain.Country;
import domain.Investigator;
import domain.Officer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class UseCaseAdministrator extends AbstractTest {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private InvestigatorService investigatorService;
	
	@Autowired
	private OfficerService officerService;
	
	@Test
	public void CreateCategoryTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "example", false, "category2", null
				}
				, { //Negative: wrong name
					"admin", "Gratuitas", false, "category2", IllegalArgumentException.class
				}, { // Negative: wrong roll
					"immigrant1", "example", false, "category2", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateCategory((String) testingData[i][0], // Username login
					(String) testingData[i][1], // name
					(Boolean) testingData[i][2], // rootCategory
					(String) testingData[i][3], // Category parent
					(Class<?>) testingData[i][4]);
		
	}
	
	protected void templateCreateCategory(final String principal,
			final String name, final Boolean rootCategory, 
			final String categoryParent, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int categoryParentId = this.getEntityId(categoryParent);
			Category categoryFinal = categoryService.findOne(categoryParentId);
			
			final Category category = this.categoryService.create();
			
			category.setName(name);
			category.setRootCategory(rootCategory);
			category.setCategoryParent(categoryFinal);

			this.categoryService.save(category);
			this.unauthenticate();
			this.categoryService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void DeleteCategoryTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "category4", null
				}
				, { //Negative: wrong category
					"investigator1", "category2", IllegalArgumentException.class
				}, { // Negative: wrong roll
					"immigrant1", "category2", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateDelete((String) testingData[i][0], // Username login
					(String) testingData[i][1], // Category
					(Class<?>) testingData[i][2]);
		
	}
	
	protected void templateCreateDelete(final String principal,
			final String category, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int categoryId = this.getEntityId(category);
			Category categoryFinal = categoryService.findOne(categoryId);
			
			this.categoryService.delete(categoryFinal);
			
			this.unauthenticate();
			this.categoryService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void CreateCountryTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "name", "isoCode", "flag", "link", null
				}
				, { //Negative: wrong link
					"admin", "name", "isoCode", "flag", "", ConstraintViolationException.class
				}, { // Negative: wrong roll
					"immigrant1", "name", "isoCode", "flag", "link", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateCountry((String) testingData[i][0], // Username login
					(String) testingData[i][1], // name
					(String) testingData[i][2], // isoCode
					(String) testingData[i][3], // flag
					(String) testingData[i][4], // link
					(Class<?>) testingData[i][5]);
		
	}
	
	protected void templateCreateCountry(final String principal,
			final String name, final String isoCode, 
			final String flag, final String link, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final Country country = this.countryService.create();
			
			country.setName(name);
			country.setIsoCode(isoCode);
			country.setFlag(flag);
			country.setLink(link);
			
			this.countryService.save(country);
			this.unauthenticate();
			this.countryService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditCountryTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "country1", "something", null
				}
				, { //Negative: wrong isoCode
					"admin", "country1", "", ConstraintViolationException.class
				}, { // Negative: wrong roll
					"immigrant1", "country1", "something", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateEditCountry((String) testingData[i][0], // Username login
					(String) testingData[i][1], // country
					(String) testingData[i][2], // isoCode
					(Class<?>) testingData[i][3]);
		
	}
	
	protected void templateEditCountry(final String principal,
			final String country, final String isoCode, 
			final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int countryId = this.getEntityId(country);
			final Country countryFinal = this.countryService.findOne(countryId);
			
			countryFinal.setIsoCode(isoCode);
			
			this.countryService.save(countryFinal);
			this.unauthenticate();
			this.countryService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void DeleteCountryTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "country3", null
				}
				, { //Negative: wrong country
					"admin", "country1", DataIntegrityViolationException.class
				}, { // Negative: wrong roll
					"immigrant1", "country3", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateDeleteCountry((String) testingData[i][0], // Username login
					(String) testingData[i][1], // country
					(Class<?>) testingData[i][2]);
		
	}
	
	protected void templateDeleteCountry(final String principal,
			final String country, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int countryId = this.getEntityId(country);
			final Country countryFinal = this.countryService.findOne(countryId);
			
			this.countryService.delete(countryFinal);
			this.unauthenticate();
			this.countryService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void RegisterInvestigatorTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "name", "surname", "email@gmail.com", "321654987", "address", null
				}
				, { // Negative: wrong email
					"admin", "name", "surname", "email", "321654987", "address", DataIntegrityViolationException.class
				}, { // Negative: wrong surname
					"admin", "name", "", "email@gmail.com", "321654987", "address", DataIntegrityViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateRegisterInvestigator((String) testingData[i][0], // Username login
					(String) testingData[i][1], // name
					(String) testingData[i][2], // surname
					(String) testingData[i][3], // email
					(String) testingData[i][4], // phoneNumber
					(String) testingData[i][5], // address
					(Class<?>) testingData[i][6]);
		
	}
	
	protected void templateRegisterInvestigator(final String principal,
			final String name, final String surname, 
			final String email, final String phoneNumber, final String address, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final Investigator investigator = this.investigatorService.create();
			
			final UserAccount userAccount = new UserAccount();
			userAccount.setUsername("userTest");
			userAccount.setPassword("userTest");
			final List<Authority> authorities = new ArrayList<>();
			final Authority aut = new Authority();
			aut.setAuthority("INVESTIGATOR");
			authorities.add(aut);
			userAccount.setAuthorities(authorities);
			
			investigator.setName(name);
			investigator.setSurname(surname);
			investigator.setEmail(email);
			investigator.setPhoneNumber(phoneNumber);
			investigator.setAddress(address);
			investigator.setUserAccount(userAccount);
			
			this.investigatorService.save(investigator);
			this.unauthenticate();
			this.investigatorService.flush();
			this.authenticate(investigator.getUserAccount().getUsername());
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void RegisterOfficerTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "name", "surname", "email@gmail.com", "321654987", "address", null
				}
				, { // Negative: wrong email
					"admin", "name", "surname", "email", "321654987", "address", DataIntegrityViolationException.class
				}, { // Negative: wrong surname
					"admin", "name", "", "email@gmail.com", "321654987", "address", DataIntegrityViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateRegisterOfficer((String) testingData[i][0], // Username login
					(String) testingData[i][1], // name
					(String) testingData[i][2], // surname
					(String) testingData[i][3], // email
					(String) testingData[i][4], // phoneNumber
					(String) testingData[i][5], // address
					(Class<?>) testingData[i][6]);
		
	}
	
	protected void templateRegisterOfficer(final String principal,
			final String name, final String surname, 
			final String email, final String phoneNumber, final String address, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final Officer officer = this.officerService.create();
			
			final UserAccount userAccount = new UserAccount();
			userAccount.setUsername("userTest");
			userAccount.setPassword("userTest");
			final List<Authority> authorities = new ArrayList<>();
			final Authority aut = new Authority();
			aut.setAuthority("OFFICER");
			authorities.add(aut);
			userAccount.setAuthorities(authorities);
			
			officer.setName(name);
			officer.setSurname(surname);
			officer.setEmail(email);
			officer.setPhoneNumber(phoneNumber);
			officer.setAddress(address);
			officer.setUserAccount(userAccount);
			
			this.officerService.save(officer);
			this.unauthenticate();
			this.officerService.flush();
			this.authenticate(officer.getUserAccount().getUsername());
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

}
