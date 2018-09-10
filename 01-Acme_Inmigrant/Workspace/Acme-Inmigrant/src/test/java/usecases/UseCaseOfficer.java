package usecases;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Application;
import domain.Decision;
import domain.Immigrant;
import domain.Investigator;
import domain.Officer;
import domain.Question;

import services.ApplicationService;
import services.DecisionService;
import services.ImmigrantService;
import services.InvestigatorService;
import services.OfficerService;
import services.QuestionService;
import services.ReportService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class UseCaseOfficer extends AbstractTest {

	@Autowired
	private OfficerService officerService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private DecisionService decisionService;
	
	@Autowired
	private ImmigrantService immigrantService;
	
	@Autowired
	private InvestigatorService investigatorService;
	
	@Autowired
	private ReportService reportService;
	
	/*
	 * 13. An actor who is authenticated as an officer must be able to:
		1. List the applications and self-assign one of them as long as it's not already assigned
			to another officer. Note that self-assigning an application that has linked applications
			results in self-assigning the whole collection of linked applications.
		2. List the applications that he or she has accepted, the applications that he or she has
			rejected, and the applications on which he or she still has to make a decision.
		3. Ask questions regarding an application that he or she has self-assigned.
		4. Make a decision on an application that he or she's self-assigned. The decision can
			be either accepting it or rejecting it. An application that is rejected must include a
			comment that justifies the rejection.
		5. Assign an investigator to investigate an immigrant who has made an application that
			he or she has self-assigned.
		6. Read the reports written by the investigators that he or she's requested to investigate
			an immigrant.
	 */
	
	// List applications 13.1
	@Test
	public void listApplications(){
		final Object testingData[][] = {
				// Positive
				{"officer1", null },
				// Negative
				{"immigrant1", null },
				// Negative
				{"investigator1", null }
				};
		for (int i =0; i<testingData.length; i++)
			this.listApplicationTemplate((String) testingData[i][0], // Username
					(Class<?>) testingData[i][1]);
	}
	
	protected void listApplicationTemplate(final String officer, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(officer);
			this.applicationService.findAll();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	// Assign 13.1
	@Test
	public void assign(){
		final Object testingData[][] = {
				// Positive
				{"officer1", "application1", null },
				// Negative //TODO: Negative assign
				{"officer2", "application1", null}
				};
		for (int i =0; i<testingData.length; i++)
			this.assignTemplate((String) testingData[i][0], // Username
					(String) testingData[i][1], // Application
					(Class<?>) testingData[i][2]);
	}
	
	protected void assignTemplate(final String officer, String application, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(officer);
			
			final int applicationId = this.getEntityId(application);
			final Application applicationFinal = this.applicationService.findOne(applicationId);
			
			final int officerId = this.getEntityId(officer);
			final Officer officerFinal = this.officerService.findOne(officerId);
			
//			this.applicationService.checkApplicationIsNotCloser(applicationId);
//			this.officerService.checkApplicationIsNotAssign(applicationId);
			this.officerService.saveApplications(officerFinal, applicationFinal);
			
			this.unauthenticate();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
//			System.out.println(oops);
		}
		this.checkExceptions(expected, caught);
	}
	
	// List applications accepted 13.2
	@Test
	public void listApplicationsAccepted(){
		final Object testingData[][] = {
				// Positive
				{"officer1", null },
				// Negative
				{"immigrant1", java.lang.NullPointerException.class },
				{"investigator1", java.lang.NullPointerException.class }
				};
		for (int i =0; i<testingData.length; i++)
			this.listApplicationAcceptedTemplate((String) testingData[i][0], // Username
					(Class<?>) testingData[i][1]);
	}
	
	protected void listApplicationAcceptedTemplate(final String officer, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(officer);
			this.questionService.findApplicationSelfAsign();
			this.decisionService.findAll();
			this.unauthenticate();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	
	// List applications rejected 13.2
	@Test
	public void listApplicationsRejected(){
		final Object testingData[][] = {
				// Positive
				{"officer1", null },
				// Negative
				{"immigrant1", java.lang.NullPointerException.class },
				{"investigator1", java.lang.NullPointerException.class }
				};
		for (int i =0; i<testingData.length; i++)
			this.listApplicationRejectedTemplate((String) testingData[i][0], // Username
					(Class<?>) testingData[i][1]);
	}
	
	protected void listApplicationRejectedTemplate(final String officer, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(officer);
			this.applicationService.findApplicationRejected();
			this.unauthenticate();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
		
	// Ask questions 13.3
	@Test
	public void askQuestion(){
		final Object testingData[][] = {
				// Positive
				{"officer1", "application1", "Pregunta?", null },
				// Negative
				{"officer1", "application2", "Pregunta?", IllegalArgumentException.class }, // pregunta en una application de otro officer
				{"officer1", "application1", "", ConstraintViolationException.class } // pregunta vacía
				};
		for (int i =0; i<testingData.length; i++)
			this.askQuestionTemplate((String) testingData[i][0], // Username
					(String) testingData[i][1], // Application
					(String) testingData[i][2], // Question
					(Class<?>) testingData[i][3]);
	}

	private void askQuestionTemplate(String officer, String application,
			String question, Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(officer);
			
			final int applicationId = this.getEntityId(application);
			final Question questionFinal;
			
			questionFinal = this.questionService.create(applicationId);
			questionFinal.setText(question);
			this.questionService.save(questionFinal);
			
			this.unauthenticate();
			this.questionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// make a decision 13.4
	@Test
	public void makeDecision(){
		final Object testingData[][] = {
				// Positive
				{"officer1", "application1", "decisionComment", true, null },
				// Negative
				{"officer1", "application2", "decisionComment", true, IllegalArgumentException.class }, // decision en application de otro officer
				{"officer1", "application1", "", false, IllegalArgumentException.class } // si es rechazada debe incluir un comentario
				};
		for (int i =0; i<testingData.length; i++)
			this.makeDecisionTemplate((String) testingData[i][0], // Username
					(String) testingData[i][1], // Application
					(String) testingData[i][2], // decisionComment
					(Boolean) testingData[i][3], // decisionAccepted
					(Class<?>) testingData[i][4]);
	}

	private void makeDecisionTemplate(String officer, String application,
			String comment, Boolean accepted, Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(officer);
			
			final int applicationId = this.getEntityId(application);
			final Decision decisionFinal;
			
			decisionFinal = this.decisionService.create(applicationId);
			decisionFinal.setComment(comment);
			this.decisionService.save(decisionFinal);
			
			this.unauthenticate();
			this.decisionService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// assign investigator 13.5
	@Test
	public void assignInvestigator(){
		final Object testingData[][] = {
				// Positive
				{"officer2", "immigrant3", "investigator1", null },
				// Negative
				{"officer1", "immigrant3", "investigator1", IllegalArgumentException.class }, // el immigrant no pertenece a las applications del officer
				{"admin", "immigrant2", "investigator2", IllegalArgumentException.class } // rol no válido
				};
		for (int i =0; i<testingData.length; i++)
			this.assignInvestigatorTemplate((String) testingData[i][0], // Username
					(String) testingData[i][1], // Immigrant
					(String) testingData[i][2], // Investigator
					(Class<?>) testingData[i][3]);
	}

	private void assignInvestigatorTemplate(String officer, String immigrant,
			String investigator, Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(officer);
			
			final int immigrantId = this.getEntityId(immigrant);
			final int investigatorId = this.getEntityId(investigator);
			
			final Investigator investigatorFinal = this.investigatorService.findOne(investigatorId);
			final Immigrant immigrantFinal = this.immigrantService.findOne(immigrantId);
			
			immigrantFinal.setInvestigator(investigatorFinal);
			this.immigrantService.assignNewInvestigator(immigrantFinal);
			this.immigrantService.save(immigrantFinal);
			
			this.unauthenticate();
			this.immigrantService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// reads reports 13.6
	@Test
	public void readsReports(){
		final Object testingData[][] = {
				// Positive
				{"officer1", "investigator1", null },
				// Negative
				{"", "investigator1", IllegalArgumentException.class }, // sin autenticar
				{"admin", "investigator1", IllegalArgumentException.class }, // roll no válido
				};
		for (int i =0; i<testingData.length; i++)
			this.readsReportsTemplate((String) testingData[i][0], // Username
					(String) testingData[i][1], // Investigator
					(Class<?>) testingData[i][2]);
	}

	private void readsReportsTemplate(String officer,
			String investigator, Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(officer);
			final int investigatorId = this.getEntityId(investigator);
			
			
			this.reportService.findReportsByInvestigatorId(investigatorId);
			
			
			this.unauthenticate();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
