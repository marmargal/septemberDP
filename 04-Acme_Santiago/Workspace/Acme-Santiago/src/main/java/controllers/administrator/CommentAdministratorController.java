package controllers.administrator;

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
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CommentService commentService;

	// Supporting services --------------------------------------------------

	// Constructors ---------------------------------------------------------

	public CommentAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Comment> comments;

		comments = this.commentService.findCommentTaboo();

		result = new ModelAndView("comment/administrator/list");
		result.addObject("comment", comments);
		result.addObject("requestURI", "comment/administrator/list.do");

		return result;
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		Collection<Comment> comments;

		comments = commentService.findAll();
		
		
		result = new ModelAndView("comment/administrator/listAll");
		result.addObject("comment", comments);
		result.addObject("requestURI", "comment/administrator/listAll.do");

		return result;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int commentId) {
		ModelAndView result;
		Comment comment;

		comment = this.commentService.findOne(commentId);
		result = this.createEditModelAndView(comment);
		result.addObject("comment", comment);

		return result;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Comment comment,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.commentService.delete(comment);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
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

		result = new ModelAndView("comment/administrator/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);
		result.addObject("requestURI", "comment/administrator/edit.do");

		return result;
	}

}