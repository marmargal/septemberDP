package usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.ReportService;
import utilities.AbstractTest;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class UseCaseInvestigator extends AbstractTest {
	
	@Autowired
	private ReportService reportService;
	
	/*
	 * 6. Investigators must write reports on the immigrants that they're assigned. For every report
	 * the system must store a piece of text plus some optional pictures.
	 */
	
	@Test
	public void CreateReportTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"investigator1", "some text", "http://www.image.com", "immigrant1", null
				}
				, { //Negative: wrong immigrant
					"investigator1", "some text", "http://www.image.com", "immigrant2", IllegalArgumentException.class
				}, { // Negative: wrong roll
					"immigrant1", "some text", "http://www.image.com", "immigrant1", IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateReport((String) testingData[i][0], // Username login
					(String) testingData[i][1], // text
					(String) testingData[i][2], // picture
					(String) testingData[i][3], // immigrant
					(Class<?>) testingData[i][4]);
		
	}
	
	protected void templateCreateReport(final String principal,
			final String text, final String picture,
			final String immigrant, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			
			final int immigrantId = this.getEntityId(immigrant);
			
			final Report report = this.reportService.create(immigrantId);
			
			report.setText(text);
			report.setPicture(picture);

			this.reportService.save(report);
			this.unauthenticate();
			this.reportService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

}
