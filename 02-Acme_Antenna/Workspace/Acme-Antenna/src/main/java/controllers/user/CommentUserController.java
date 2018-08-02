package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

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
	
	// Listing -----------------------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tutorialId){
		ModelAndView res;
		
		Tutorial tutorial;
		tutorial = tutorialService.findOne(tutorialId);
		
		Collection<Comment> comments = new ArrayList<Comment>();
		comments.addAll(tutorial.getComments());
		
		res = new ModelAndView("comment/list");
		res.addObject("comments",comments);
		res.addObject("tutorial",tutorial);
		res.addObject("requestURI","comment/user/list.do");
		
		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tutorialId) {
		ModelAndView res;
		Comment comment;
		Tutorial tutorial;

		comment = this.commentService.create();
		
		tutorial = tutorialService.findOne(tutorialId);
		comment.setTutorial(tutorial);

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
