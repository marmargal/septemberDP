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
		
		Collection<Comment> commentsFinal = new ArrayList<Comment>();
		for(Comment c: comments){
			if(c.getCommentParent() == null){
				commentsFinal.add(c);
			}
		}
		
		res = new ModelAndView("comment/list");
		res.addObject("comments",commentsFinal);
		res.addObject("tutorial",tutorial);
		res.addObject("requestURI","comment/user/list.do");
		
		return res;
	}
	
	@RequestMapping(value = "/listReplies", method = RequestMethod.GET)
	public ModelAndView listReplies(@RequestParam final int commentId) {
		ModelAndView result;
		Collection<Comment> comments;

		final Comment c = this.commentService.findOne(commentId);

		comments = c.getReplies();

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", "comment/list.do");

		return result;
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
	
	@RequestMapping(value = "/createReply", method = RequestMethod.GET)
	public ModelAndView createReply(@RequestParam final int commentId) {
		ModelAndView res;
		if (this.commentService.findOne(commentId) == null)
			res = new ModelAndView("redirect:../../");
		else {
			Comment result;

			Comment commentParent;

			commentParent = this.commentService.findOne(commentId);

			result = this.commentService.create();
			result.setCommentParent(commentParent);
			result.setTutorial(commentParent.getTutorial());
			res = this.createEditModelAndView(result);
		}
		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
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
		result.addObject("requestUri", "comment/user/edit.do");

		return result;
	}
}
