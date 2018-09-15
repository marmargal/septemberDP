package usecases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.AnswerService;
import services.ApplicationService;
import services.ContactSectionService;
import services.EducationSectionService;
import services.PersonalSectionService;
import services.SocialSectionService;
import services.WorkSectionService;
import utilities.AbstractTest;
import domain.Answer;
import domain.Application;
import domain.ContactSection;
import domain.CreditCard;
import domain.EducationSection;
import domain.PersonalSection;
import domain.SocialSection;
import domain.WorkSection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class UseCaseImmigrant extends AbstractTest {
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private ContactSectionService contactSectionService;
	
	@Autowired
	private EducationSectionService educationSectionService;
	
	@Autowired
	private SocialSectionService socialSectionService;
	
	@Autowired
	private PersonalSectionService personalSectionService;
	
	@Autowired
	private WorkSectionService workSectionService;
	
	/*
	 * 12. An actor who is authenticated as an immigrant must be able to:
			1. Manage an arbitrary number of applications, which includes opening them, closing
			them, editing them, linking them to applications by other users, and displaying
			them. Once an application is opened, it can be edited until it is closed. Closing an
			application that is linked to others closes them all immediately.
			2. Answer the questions that an officer has posed on any of his or her applications.
			3. List his or her applications according to whether they are opened, closed and accepted, 
			closed and rejected, or closed and awaiting a decision.
	 */
	
	@Test
	public void CreateApplicationTest() {
		
		final Object testingData[][] = {
				{// Negative
					"investigator1", 2.0, false, IllegalArgumentException.class
				}, {//Negative
					"officer1", 3.0, false, IllegalArgumentException.class
				}, {//Negative
					"officer2", 3.0, false, IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateApplication((String) testingData[i][0], // Username login
					(Double) testingData[i][1], //statistics
					(Boolean) testingData[i][2], // closed
					(Class<?>) testingData[i][3]);
		
	}
	
	protected void templateCreateApplication(final String principal,
			final Double statistics,
			final Boolean closed, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			final Application application = this.applicationService.create();
			
			CreditCard cc = new CreditCard();
			cc.setBrandName("MasterCard");
			cc.setCvv(334);
			cc.setExpirationMonth(12);
			cc.setExpirationYear(2019);
			cc.setHolderName("Raul");
			cc.setNumber("4111111111111111");

			application.setCreditCard(cc);
			application.setStatistics(statistics);
			application.setClosed(closed);
			
			String birthDate = "12/12/2010";
			final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			final Date d1 = format.parse(birthDate);
			
			PersonalSection personalSection = new PersonalSection();
			personalSection.setBirthDate(d1);
			personalSection.setBirthPlace("Sevilla");
			Collection<String> names = new ArrayList<>();
			names.add("Daniel");
			personalSection.setNames(names);
			personalSection.setPicture("http://www.imagen.com");
			
			List<SocialSection> socialSections = new ArrayList<SocialSection>();
			SocialSection socialSection = new SocialSection();
			socialSection.setNickName("nickname");
			socialSection.setSocialNetwork("http://www.twitter.com");
			socialSection.setProfileLink("http://www.twitter.com");
			socialSections.add(socialSection);
			
			
			application.setSocialSection(socialSections);
			application.setPersonalSection(personalSection);
			socialSection.setApplication(application);

//			this.personalSectionService.save(personalSection);
//			this.socialSectionService.save(socialSection);
			this.applicationService.save(application);
			
			this.unauthenticate();
			this.applicationService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditApplicationTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", "application1", "Sevilla", null
			}, { // Negative: wrong roll
				"officer1", "application1", "Sevilla", IllegalArgumentException.class }
			, { // Negative: wrong roll
				"immigrant1", "application2", "Sevilla", IllegalArgumentException.class }
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditApplication((String) testingData[i][0], // Username
				(String) testingData[i][1], // applicationId
				(String) testingData[i][2], // PersonalSection birthPlace
				(Class<?>) testingData[i][3]);
	}
	
	protected void templateEditApplication(final String immigrant, final String application, final String birthPlace, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(immigrant);
			
			final int applicationId = this.getEntityId(application);
			final Application applicationFinal = this.applicationService.findOne(applicationId);
			
			applicationFinal.getPersonalSection().setBirthPlace(birthPlace);
			
			this.applicationService.save(applicationFinal);
			this.unauthenticate();
			this.applicationService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void ListApplicationTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", null
			}, { // Negative: wrong roll
				"officer1", null }
		};

		for (int i = 0; i < testingData.length; i++)
			this.listApplicationTemplate((String) testingData[i][0], // Username
				(Class<?>) testingData[i][1]);
	}
	
	protected void listApplicationTemplate(final String immigrant, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(immigrant);
			this.applicationService.findAll();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void ListClosedApplicationTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", null
			}, { // Negative: wrong roll
				"officer1", IllegalArgumentException.class }
			, { // Negative: wrong roll
				"investigator1", IllegalArgumentException.class }
		};

		for (int i = 0; i < testingData.length; i++)
			this.listClosedApplicationTemplate((String) testingData[i][0], // Username
				(Class<?>) testingData[i][1]);
	}
	
	protected void listClosedApplicationTemplate(final String immigrant, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(immigrant);
			this.applicationService.findApplicationClosed();
			this.unauthenticate();
			this.applicationService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void ListRejectedApplicationTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", null
			}, { // Negative: wrong roll
				"officer1", IllegalArgumentException.class }
			, { // Negative: wrong roll
				"investigator1", IllegalArgumentException.class }
		};

		for (int i = 0; i < testingData.length; i++)
			this.listRejectedApplicationTemplate((String) testingData[i][0], // Username
				(Class<?>) testingData[i][1]);
	}
	
	protected void listRejectedApplicationTemplate(final String immigrant, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(immigrant);
			this.applicationService.findApplicationRejectedbyImmigrant();
			this.unauthenticate();
			this.applicationService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void ListAcceptedApplicationTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", null
			}, { // Negative: wrong roll
				"officer1", IllegalArgumentException.class }
			, { // Negative: wrong roll
				"investigator1", IllegalArgumentException.class }
		};

		for (int i = 0; i < testingData.length; i++)
			this.listAcceptedApplicationTemplate((String) testingData[i][0], // Username
				(Class<?>) testingData[i][1]);
	}
	
	protected void listAcceptedApplicationTemplate(final String immigrant, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(immigrant);
			this.applicationService.findApplicationAccepted();
			this.unauthenticate();
			this.applicationService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	@Test
	public void CreateAnswerTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"immigrant1", "reply", "question2", null
				}, { // Negative: wrong roll
					"investigator1", "reply", "question1", IllegalArgumentException.class
				}, { // Negative: wrong roll
					"officer1", "reply", "question1", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateAnswer((String) testingData[i][0], // Username login
					(String) testingData[i][1], // reply
					(String) testingData[i][2], // Question
					(Class<?>) testingData[i][3]);
		
	}
	
	protected void templateCreateAnswer(final String principal,
			final String reply, final String question, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int questionId = this.getEntityId(question);
			final Answer answer = this.answerService.create(questionId);
			
			answer.setReply(reply);
			
			this.answerService.save(answer);
			this.unauthenticate();
			this.answerService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void CreateContactSectionTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"immigrant1", "email@gmail.com", "952952952", "12", "application1", null
				}, { // Negative: wrong roll
					"officer1", "email@gmail.com", "952952952", "12", "application1", IllegalArgumentException.class
				}, { // Negative: wrong email
					"immigrant1", "email", "952952952", "12", "application1", ConstraintViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateContactSection((String) testingData[i][0], // Username login
					(String) testingData[i][1], // email
					(String) testingData[i][2], // phone
					(Integer) Integer.parseInt((String) testingData[i][3]), // numberPage
					(String) testingData[i][4], // Application
					(Class<?>) testingData[i][5]);
		
	}
	
	protected void templateCreateContactSection(final String principal,
			final String email, final String phone, final Integer numberPage, final String application, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int applicationId = this.getEntityId(application);
			final Application applicationFinal = this.applicationService.findOne(applicationId);
			final ContactSection contactSection = this.contactSectionService.create();
			
			contactSection.setEmail(email);
			contactSection.setPhoneNumber(phone);
			contactSection.setPageNumber(numberPage);
			contactSection.setApplication(applicationFinal);
			
			this.contactSectionService.save(contactSection);
			this.unauthenticate();
			this.contactSectionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditContactSectionTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", "contactSection1", "dani@gmail.com", null
			}
			, { // Negative: wrong roll
				"officer1", "contactSection1", "dani@gmail.com", IllegalArgumentException.class }
			, { // Negative: wrong roll
				"immigrant2", "contactSection1", "dani@gmail.com", IllegalArgumentException.class }
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditContactSection((String) testingData[i][0], // Username
				(String) testingData[i][1], // contactSection
				(String) testingData[i][2], // email
				(Class<?>) testingData[i][3]);
	}
	
	protected void templateEditContactSection(final String immigrant, final String contactSection, final String email, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(immigrant);
			
			final int contactSectionId = this.getEntityId(contactSection);
			final ContactSection contactSectionFinal = this.contactSectionService.findOne(contactSectionId);
			
			contactSectionFinal.setEmail(email);
			
			this.contactSectionService.save(contactSectionFinal);
			this.unauthenticate();
			this.contactSectionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void CreateEducationSectionTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"immigrant1", "nameDegree", "institution", "10/10/2015", "PRIMARY", "application1", null
				}, { // Negative: wrong roll
					"officer1", "nameDegree", "institution", "10/10/2015", "PRIMARY", "application1", IllegalArgumentException.class
				}, { // Negative: wrong email
					"immigrant1", "", "institution", "10/10/2015", "PRIMARY", "application1", ConstraintViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateEducationSection((String) testingData[i][0], // Username login
					(String) testingData[i][1], // nameDegree
					(String) testingData[i][2], // institution
					(String) testingData[i][3], // dateAwarded
					(String) testingData[i][4], // level
					(String) testingData[i][5], // Application
					(Class<?>) testingData[i][6]);
		
	}
	
	protected void templateCreateEducationSection(final String principal,
			final String nameDegree, final String institution, 
			final String dateAwarded, final String level, 
			final String application, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int applicationId = this.getEntityId(application);
			final Application applicationFinal = this.applicationService.findOne(applicationId);
			final EducationSection educationSection = this.educationSectionService.create();
			
			final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			educationSection.setDateAwarded(format.parse(dateAwarded));
			educationSection.setNameDegree(nameDegree);
			educationSection.setInstitution(institution);
			educationSection.setLevel(level);
			educationSection.setApplication(applicationFinal);
			
			this.educationSectionService.save(educationSection);
			this.unauthenticate();
			this.educationSectionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditEducationSectionTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", "educationSection1", "nameDegree", null
			}
			, { // Negative: wrong roll
				"officer1", "educationSection1", "nameDegree", IllegalArgumentException.class }
			, { // Negative: wrong roll
				"immigrant2", "educationSection1", "", ConstraintViolationException.class }
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditEducationSection((String) testingData[i][0], // Username
				(String) testingData[i][1], // educationSection
				(String) testingData[i][2], // nameDegree
				(Class<?>) testingData[i][3]);
	}
	
	protected void templateEditEducationSection(final String immigrant, final String educationSection, final String nameDegree, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(immigrant);
			
			final int educationSectionId = this.getEntityId(educationSection);
			final EducationSection educationSectionFinal = this.educationSectionService.findOne(educationSectionId);
			
			educationSectionFinal.setNameDegree(nameDegree);
			
			this.educationSectionService.save(educationSectionFinal);
			this.unauthenticate();
			this.educationSectionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void CreateSocialSectionTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"immigrant1", "nickName", "socialNetwork", "http://www.profile.com", "application1", null
				}, { // Negative: wrong roll
					"officer1", "nickName", "socialNetwork", "http://www.profile.com", "application1", IllegalArgumentException.class
				}, { // Negative: wrong nickName
					"immigrant1", "", "socialNetwork", "http://www.profile.com", "application1", ConstraintViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateSocialSection((String) testingData[i][0], // Username login
					(String) testingData[i][1], // nickName
					(String) testingData[i][2], // socialNetwork
					(String) testingData[i][3], // profileLink
					(String) testingData[i][4], // Application
					(Class<?>) testingData[i][5]);
		
	}
	
	protected void templateCreateSocialSection(final String principal,
			final String nickName, final String socialNetwork, 
			final String profileLink, 
			final String application, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int applicationId = this.getEntityId(application);
			final Application applicationFinal = this.applicationService.findOne(applicationId);
			final SocialSection socialSection = this.socialSectionService.create();
			
			socialSection.setNickName(nickName);
			socialSection.setSocialNetwork(socialNetwork);
			socialSection.setProfileLink(profileLink);
			socialSection.setApplication(applicationFinal);
			
			this.socialSectionService.save(socialSection);
			this.unauthenticate();
			this.socialSectionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditSocialSectionTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", "socialSection1", "nickName", null
			}
			, { // Negative: wrong roll
				"officer1", "socialSection1", "nickName", IllegalArgumentException.class }
			, { // Negative: wrong nickName
				"immigrant2", "socialSection1", "", ConstraintViolationException.class }
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditSocialSection((String) testingData[i][0], // Username
				(String) testingData[i][1], // educationSection
				(String) testingData[i][2], // nameDegree
				(Class<?>) testingData[i][3]);
	}
	
	protected void templateEditSocialSection(final String immigrant, final String socialSection, final String nickName, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(immigrant);
			
			final int socialSectionId = this.getEntityId(socialSection);
			final SocialSection socialSectionFinal = this.socialSectionService.findOne(socialSectionId);
			
			socialSectionFinal.setNickName(nickName);
			
			this.socialSectionService.save(socialSectionFinal);
			this.unauthenticate();
			this.socialSectionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void CreateWorkSectionTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"immigrant1", "nameCompany", "position", "01/01/2000", "01/01/2001", "application1", null
				}, { // Negative: wrong roll
					"officer1", "nameCompany", "position", "01/01/2000", "01/01/2001", "application1", IllegalArgumentException.class
				}, { // Negative: wrong nameCompany
					"immigrant1", "", "position", "01/01/2000", "01/01/2001", "application1", ConstraintViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateWorkSection((String) testingData[i][0], // Username login
					(String) testingData[i][1], // nameCompany
					(String) testingData[i][2], // position
					(String) testingData[i][3], // startDate
					(String) testingData[i][4], // endDate
					(String) testingData[i][5], // Application
					(Class<?>) testingData[i][6]);
		
	}
	
	protected void templateCreateWorkSection(final String principal,
			final String nameCompany, final String position, 
			final String startDate, final String endDate, 
			final String application, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int applicationId = this.getEntityId(application);
			final Application applicationFinal = this.applicationService.findOne(applicationId);
			final WorkSection workSection = this.workSectionService.create();
			
			workSection.setNameCompany(nameCompany);
			workSection.setPosition(position);
			workSection.setApplication(applicationFinal);
			final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			workSection.setStartDate(format.parse(startDate));
			workSection.setEndDate(format.parse(endDate));
			
			this.workSectionService.save(workSection);
			this.unauthenticate();
			this.workSectionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditWorkSectionTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", "workSection1", "nameCompany", null
			}
			, { // Negative: wrong roll
				"officer1", "workSection1", "nameCompany", IllegalArgumentException.class }
			, { // Negative: wrong nameCompany
				"immigrant2", "workSection1", "", ConstraintViolationException.class }
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditWorkSection((String) testingData[i][0], // Username
				(String) testingData[i][1], // workSection
				(String) testingData[i][2], // nameCompany
				(Class<?>) testingData[i][3]);
	}
	
	protected void templateEditWorkSection(final String immigrant, final String workSection, final String nameCompany, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(immigrant);
			
			final int workSectionId = this.getEntityId(workSection);
			final WorkSection workSectionFinal = this.workSectionService.findOne(workSectionId);
			
			workSectionFinal.setNameCompany(nameCompany);
			
			this.workSectionService.save(workSectionFinal);
			this.unauthenticate();
			this.workSectionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}


}
