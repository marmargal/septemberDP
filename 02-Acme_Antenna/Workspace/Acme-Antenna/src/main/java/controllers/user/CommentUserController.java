package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.TutorialService;
import controllers.AbstractController;
import domain.Comment;
import domain.Tutorial;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CommentService commentService;

	@Autowired
	private TutorialService tutorialService;

	// Constructors ---------------------------------------------------------

	public CommentUserController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tutorialId) {
		ModelAndView res;
		Comment comment;
		Tutorial tutorial;

		comment = this.commentService.create();
		
		System.out.println("Comment después del create: " + comment);
		tutorial = tutorialService.findOne(tutorialId);
		System.out.println("Tutorial después del findOne: " + tutorial);
		comment.setTutorial(tutorial);
		System.out.println("comment.setTutorial: " + comment.getTutorial());
		System.out.println("tutorial.getComments: " + tutorial.getComments());
		System.out.println("=====================================================");
		System.out.println(comment.getPictures());
		System.out.println(comment.getText());
		System.out.println(comment.getTitle());
		System.out.println(comment.getCommentParent());
		System.out.println(comment.getMoment());
		System.out.println(comment.getReplies());
		System.out.println(comment.getTutorial());
		System.out.println(comment.getUser());

		res = this.createEditModelAndView(comment);

		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comment comment, final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		if (binding.hasErrors())
			res = this.createEditModelAndView(comment, "comment.params.error");
		else
			try {
				this.commentService.save(comment);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this.createEditModelAndView(comment,
						"comment.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);
		result.addObject("requestUri", "comment/user/create.do");

		return result;
	}
}
