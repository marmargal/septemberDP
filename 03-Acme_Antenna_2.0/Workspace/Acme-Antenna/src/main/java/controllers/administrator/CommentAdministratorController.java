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
public class CommentAdministratorController extends AbstractController{

	@Autowired
	private CommentService commentService;

	public CommentAdministratorController(){
		super();
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int commentId) {
		ModelAndView result;
		Comment comment;

		comment = this.commentService.findOne(commentId);
		result = this.createEditModelAndView(comment);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Comment comment, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(comment, "comment.params.error");
		} else {
			try {
				this.commentService.delete(comment);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(comment, "comment.commit.error");
			}
		}
		
		return res;
	}
	
	@RequestMapping(value="/commentsTabooList", method=RequestMethod.GET)
	public ModelAndView commentsTabooList(){
		ModelAndView res;
		Collection<Comment> comments = this.commentService.commentsTaboo();
		
		res = new ModelAndView("comment/list");
		res.addObject("comments",comments);
		res.addObject("requestURI","comment/administrator/commentsTabooList.do");
		
		return res;
	}
	
	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment, final String message) {
		ModelAndView result;

		result = new ModelAndView("comment/administrator/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);
		result.addObject("requestUri", "comment/administrator/edit.do");

		return result;
	}
}
