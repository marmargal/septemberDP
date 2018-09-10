package usecases;

import java.text.SimpleDateFormat;
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
import services.LawService;
import services.OfficerService;
import services.RequirementService;
import services.VisaService;
import utilities.AbstractTest;
import domain.Category;
import domain.Country;
import domain.Investigator;
import domain.Law;
import domain.Officer;
import domain.Requirement;
import domain.Visa;

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
	
	@Autowired
	private LawService lawService;
	
	@Autowired
	private RequirementService requirementService;
	
	@Autowired
	private VisaService visaService;
	
	/*
	 * 14. An actor who is authenticated as an administrator must be able to:
			1. Create user accounts for new officers and investigators.
			2. Manage the catalogue of visas, which includes creating them, editing them, listing
			them, and abrogating them.
	 */
	
	/*
	 * 26. An actor who is authenticated as an administrator must be able to:4
			1. Manage the catalogue of countries, which includes creating them, editing them, deleting them, 
			listing them, and registering the visas that they offer.
			2. Manage the hierarchy of visas, which includes creating, editing, deleting, and listing
			them, as well as assigning visas to categories.
			3. Manage the catalogue of laws, which includes creating, editing, deleting, and listing
			them, as well as assigning them to countries and requirements.
			4. Manage the catalogue of requirements, which includes creating, editing, deleting,
			and listing them, as well as assigning them to visas and laws
	 */
	
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
	
	@Test
	public void CreateLawTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "example", "example", "01/01/1993", "02/02/1993", null
				}
				, { //Negative: wrong roll
					"immigrant1", "example", "example", "01/01/1993", "02/02/2000", IllegalArgumentException.class
				}, { // Negative: wrong text
					"admin", "", "example", "01/01/1993", "02/02/2000", ConstraintViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateLaw((String) testingData[i][0], // Username login
					(String) testingData[i][1], // title
					(String) testingData[i][2], // text
					(String) testingData[i][3], // enactmentDate
					(String) testingData[i][4], // abrogationTime
					(Class<?>) testingData[i][5]);
		
	}
	
	protected void templateCreateLaw(final String principal,
			final String title, final String text, 
			final String enactmentDate, final String abrogationTime, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final Law law = this.lawService.create();
			
			law.setTitle(title);
			law.setText(text);
			final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			law.setEnactmentDate(format.parse(enactmentDate));
			law.setAbrogationTime(format.parse(abrogationTime));
			law.setCountry(this.countryService.findOne(this.getEntityId("country1")));

			this.lawService.save(law);
			this.unauthenticate();
			this.lawService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditLawTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "law1", "example", null
				}
				, { //Negative: wrong roll
					"immigrant1", "law1", "example", IllegalArgumentException.class
				}, { // Negative: wrong text
					"admin", "law1", "", ConstraintViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateEditLaw((String) testingData[i][0], // Username login
					(String) testingData[i][1], // law
					(String) testingData[i][2], // title
					(Class<?>) testingData[i][3]);
		
	}
	
	protected void templateEditLaw(final String principal,
			final String law, final String title, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int lawId = this.getEntityId(law);
			final Law lawFinal = this.lawService.findOne(lawId);
			
			lawFinal.setTitle(title);

			this.lawService.save(lawFinal);
			this.unauthenticate();
			this.lawService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void DeleteLawTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "law1", null
				}
				, { //Negative: wrong roll
					"immigrant1", "law1", IllegalArgumentException.class
				}, { // Negative: wrong text
					"investigator1", "law1", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateDeleteLaw((String) testingData[i][0], // Username login
					(String) testingData[i][1], // law
					(Class<?>) testingData[i][2]);
		
	}
	
	protected void templateDeleteLaw(final String principal,
			final String law, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int lawId = this.getEntityId(law);
			final Law lawFinal = this.lawService.findOne(lawId);
			
			this.lawService.delete(lawFinal);
			this.unauthenticate();
			this.lawService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void CreateRequirementTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "title", "description", false, "law1", null
				}
				, { //Negative: wrong roll
					"immigrant1", "title", "description", false, "law1", IllegalArgumentException.class
				}, { // Negative: wrong text
					"admin", "", "description", false, "law1", ConstraintViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateRequirement((String) testingData[i][0], // Username login
					(String) testingData[i][1], // title
					(String) testingData[i][2], // description
					(Boolean) testingData[i][3], // abrogated
					(String) testingData[i][4], // law
					(Class<?>) testingData[i][5]);
		
	}
	
	protected void templateCreateRequirement(final String principal,
			final String title, final String description, 
			final Boolean abrogated, final String law, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final Requirement requirement = this.requirementService.create();
			
			final int lawId = this.getEntityId(law);
			final Law lawFinal = this.lawService.findOne(lawId);
			
			requirement.setTitle(title);
			requirement.setDescription(description);
			requirement.setAbrogated(abrogated);
			requirement.setLaw(lawFinal);
			
			this.requirementService.save(requirement);
			this.unauthenticate();
			this.requirementService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditRequirementTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "requirement1", "description", null
				}
				, { //Negative: wrong roll
					"immigrant1", "requirement1", "something", IllegalArgumentException.class
				}, { // Negative: wrong description
					"admin", "requirement1", "", ConstraintViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateEditRequirement((String) testingData[i][0], // Username login
					(String) testingData[i][1], // requirement
					(String) testingData[i][2], // description
					(Class<?>) testingData[i][3]);
		
	}
	
	protected void templateEditRequirement(final String principal,
			final String requirement, final String description, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int requirementId = this.getEntityId(requirement);
			final Requirement requirementFinal = this.requirementService.findOne(requirementId);
			
			requirementFinal.setDescription(description);
			
			this.requirementService.save(requirementFinal);
			this.unauthenticate();
			this.requirementService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void DeleteRequirementTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "requirement1", null
				}
				, { //Negative: wrong roll
					"immigrant1", "requirement1", IllegalArgumentException.class
				}, { // Negative: wrong roll
					"investigator1", "requirement1", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateDeleteRequirement((String) testingData[i][0], // Username login
					(String) testingData[i][1], // requirement
					(Class<?>) testingData[i][2]);
		
	}
	
	protected void templateDeleteRequirement(final String principal,
			final String requirement, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int requirementId = this.getEntityId(requirement);
			final Requirement requirementFinal = this.requirementService.findOne(requirementId);
			
			this.requirementService.delete(requirementFinal);
			this.unauthenticate();
			this.requirementService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void CreateVisaTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "classes", "description", "23", "category1", "country1", null
				}
//				, { //Negative: wrong roll
//					"immigrant1", "classes", "description", "23", "category1", "country1", IllegalArgumentException.class
//				}, { // Negative: wrong text
//					"admin", "", "description", "23", "category1", "country1", ConstraintViolationException.class
//				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateVisa((String) testingData[i][0], // Username login
					(String) testingData[i][1], // classes
					(String) testingData[i][2], // description
					(Integer) Integer.parseInt((String) testingData[i][3]), // price
					(String) testingData[i][4], // category
					(String) testingData[i][5], // country
					(Class<?>) testingData[i][6]);
		
	}
	
	protected void templateCreateVisa(final String principal,
			final String classes, final String description, 
			final Integer price, final String category, final String country, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final Visa visa = this.visaService.create();
			
			final int categoryId = this.getEntityId(category);
			final Category categoryFinal = this.categoryService.findOne(categoryId);
			
			final int countryId = this.getEntityId(country);
			final Country countryFinal = this.countryService.findOne(countryId);
			
			visa.setClasses(classes);
			visa.setDescription(description);
			visa.setPrice(price);
			visa.setCategory(categoryFinal);
			visa.setCountry(countryFinal);
			
			this.visaService.save(visa);
			this.unauthenticate();
			this.visaService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditVisaTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"admin", "visa1", "description", null
				}
				, { //Negative: wrong roll
					"immigrant1", "visa1", "something", IllegalArgumentException.class
				}, { // Negative: wrong description
					"admin", "visa1", "", ConstraintViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateEditVisa((String) testingData[i][0], // Username login
					(String) testingData[i][1], // visa
					(String) testingData[i][2], // description
					(Class<?>) testingData[i][3]);
		
	}
	
	protected void templateEditVisa(final String principal,
			final String visa, final String description, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int visaId = this.getEntityId(visa);
			final Visa visaFinal = this.visaService.findOne(visaId);
			
			visaFinal.setDescription(description);
			
			this.visaService.save(visaFinal);
			this.unauthenticate();
			this.visaService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

}
