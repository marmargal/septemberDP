package tests;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CommentService;
import services.TutorialService;
import utilities.AbstractTest;
import domain.Comment;
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class AdministratorTest extends AbstractTest {

	@Autowired
	private TutorialService tutorialService;
	
	@Autowired
	private CommentService commentService;
	
	
	// 16.1 Remove a tutorial that he or she thinks is inappropriate
	
	@Test
	public void removeTutorialTest() {

		final Object testingData[][] = {
				
				// Un administrador elimina un tutorial	
				
			{	// Positivo
				"admin", "tutorial1", null
			}, {// Negativo (tutorial que no existe)
				"administrator1", "tutorial8", IllegalArgumentException.class
			}, {// Negativo (admin que no existe)
				"administrator1", "tutorial1", IllegalArgumentException.class
			}
			
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRemoveTutorialTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateRemoveTutorialTest(final String administrator, final String tutorialEntity, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(administrator);
			final int tutorialId = this.getEntityId(tutorialEntity);
			final Tutorial tutorial = this.tutorialService.findOne(tutorialId);

			this.tutorialService.delete(tutorial);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 16.2 Remove a comment that he or she thinks is inappropriate.
	
	@Test
	public void removeCommentTest() {

		final Object testingData[][] = {
				
				// Un administrador elimina un comentario	
				
			{	// Positivo
				"admin", "comment1", null
			}, {// Negativo (comentario que no existe)
				"administrator1", "comment8", IllegalArgumentException.class
			}, {// Negativo (admin que no existe)
				"administrator1", "comment1", IllegalArgumentException.class
			}
			
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRemoveCommentTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateRemoveCommentTest(final String administrator, final String commentEntity, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(administrator);
			final int commentId = this.getEntityId(commentEntity);
			final Comment comment = this.commentService.findOne(commentId);

			this.commentService.delete(comment);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
