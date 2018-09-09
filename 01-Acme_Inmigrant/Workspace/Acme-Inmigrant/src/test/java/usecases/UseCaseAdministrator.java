package usecases;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.CategoryService;
import services.CountryService;
import utilities.AbstractTest;
import domain.Category;
import domain.Country;

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
					"admin", "country1", null
				}
//				, { //Negative: wrong isoCode
//					"investigator1", "country1", IllegalArgumentException.class
//				}, { // Negative: wrong roll
//					"immigrant1", "country1", IllegalArgumentException.class
//				}
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

}
