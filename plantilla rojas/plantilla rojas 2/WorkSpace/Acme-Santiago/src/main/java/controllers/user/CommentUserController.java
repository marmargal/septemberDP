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
import services.HikeService;
import services.RouteService;
import controllers.AbstractController;
import domain.Comment;
import domain.Hike;
import domain.Route;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CommentService commentService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private HikeService hikeService;

	// Constructors ---------------------------------------------------------

	public CommentUserController() {
		super();
	}

	// Listing -----------------------------------
	@RequestMapping(value = "/listRoute", method = RequestMethod.GET)
	public ModelAndView listRoute(
			@RequestParam(defaultValue = "0") final int routeId) {
		ModelAndView res;

		Route route;

		if (routeId == 0) {
			res = new ModelAndView("redirect:../../");

		} else if (this.routeService.findOne(routeId) == null) {
			res = new ModelAndView("redirect:../../");

		} else {
			route = routeService.findOne(routeId);

			Collection<Comment> comment = new ArrayList<Comment>();
			comment.addAll(route.getComments());

			res = new ModelAndView("comment/user/listRoute");
			res.addObject("comment", comment);
			res.addObject("route", route);
			res.addObject("requestURI", "comment/user/listRoute.do");

		}
		return res;
	}

	// Listing -----------------------------------
	@RequestMapping(value = "/listHike", method = RequestMethod.GET)
	public ModelAndView listHike(
			@RequestParam(defaultValue = "0") final int hikeId) {
		ModelAndView res;

		Hike hike;

		if (hikeId == 0) {
			res = new ModelAndView("redirect:../../");

		} else if (this.hikeService.findOne(hikeId) == null) {
			res = new ModelAndView("redirect:../../");
		} else {
			hike = hikeService.findOne(hikeId);

			Collection<Comment> comment = new ArrayList<Comment>();
			comment.addAll(hike.getComments());

			res = new ModelAndView("comment/user/listHike");
			res.addObject("comment", comment);
			res.addObject("hike", hike);
			res.addObject("requestURI", "comment/user/listHike.do");
		}
		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/createRoute", method = RequestMethod.GET)
	public ModelAndView createRoute(
			@RequestParam(defaultValue = "0") final int routeId) {
		ModelAndView res;
		Comment comment;
		Route route;
		if (routeId == 0) {
			res = new ModelAndView("redirect:../../");

		} else if (this.routeService.findOne(routeId) == null) {
			res = new ModelAndView("redirect:../../");
		} else {

			comment = this.commentService.create();

			route = routeService.findOne(routeId);
			comment.setRoute(route);

			

			res = this.createEditModelAndViewRoute(comment);
		}
		return res;
	}

	@RequestMapping(value = "/createHike", method = RequestMethod.GET)
	public ModelAndView createHike(@RequestParam(defaultValue = "0")  final int hikeId) {
		ModelAndView res;
		Comment comment;
		Hike hike;
		
		if (hikeId == 0) {
			res = new ModelAndView("redirect:../../");

		} else if (this.hikeService.findOne(hikeId) == null) {
			res = new ModelAndView("redirect:../../");
		} else {

		comment = this.commentService.create();

		hike = hikeService.findOne(hikeId);
		comment.setHike(hike);

		

		res = this.createEditModelAndViewHike(comment);
		}
		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/editRoute", method = RequestMethod.POST, params = "save")
	public ModelAndView saveRoute(@Valid Comment comment,
			final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		if (binding.hasErrors())
			res = this.createEditModelAndViewRoute(comment,
					"comment.params.error");
		else
			try {
				this.commentService.save(comment);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this.createEditModelAndViewRoute(comment,
						"comment.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/editHike", method = RequestMethod.POST, params = "save")
	public ModelAndView saveHike(@Valid Comment comment,
			final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		if (binding.hasErrors())
			res = this.createEditModelAndViewHike(comment,
					"comment.params.error");
		else
			try {
				this.commentService.save(comment);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this.createEditModelAndViewHike(comment,
						"comment.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndViewRoute(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndViewRoute(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewRoute(final Comment comment,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("comment/user/editRoute");
		result.addObject("comment", comment);
		result.addObject("message", message);
		result.addObject("requestUri", "comment/user/createRoute.do");

		return result;
	}

	protected ModelAndView createEditModelAndViewHike(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndViewHike(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewHike(final Comment comment,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("comment/user/editHike");
		result.addObject("comment", comment);
		result.addObject("message", message);
		result.addObject("requestUri", "comment/user/createHike.do");

		return result;
	}
}
