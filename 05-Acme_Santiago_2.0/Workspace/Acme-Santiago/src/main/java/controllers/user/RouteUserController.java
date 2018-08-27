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

import services.RouteService;
import controllers.AbstractController;
import domain.Hike;
import domain.Route;

@Controller
@RequestMapping("/route/user")
public class RouteUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private RouteService routeService;

	// @Autowired
	// private UserService userService;

	// Constructors ---------------------------------------------------------

	public RouteUserController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Route route;
		route = this.routeService.create();
		res = this.createEditModelAndView(route);
		return res;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(defaultValue = "0") final int routeId) {
		ModelAndView result;
		Route route;
		if (routeId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.routeService.findOne(routeId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			route = this.routeService.findOne(routeId);
			// final User user = this.userService.findByPrincipal();
			// if (user.getRoute().contains(route))
			result = this.createEditModelAndView(route);
			// else
			// result = new ModelAndView("redirect:../../");
		}
		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Route route, final BindingResult binding) {
		ModelAndView res;
		// route = this.routeService.reconstruct(route, binding);
		if (binding.hasErrors())
			res = this.createEditModelAndView(route, "route.params.error");
		else
			try {
				this.routeService.save(route);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(route, "route.commit.error");
			}
		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Route route,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.routeService.delete(route);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(route, "route.commit.error");
		}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Route route) {
		ModelAndView result;

		result = this.createEditModelAndView(route, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Route route,
			final String message) {
		ModelAndView result;

		Collection<Hike> hikes = new ArrayList<Hike>();
		hikes = this.routeService.hikesWithoutRoute();

		Collection<Hike> routeHikes = new ArrayList<Hike>();
		routeHikes = route.getHikes();

		result = new ModelAndView("route/edit");
		result.addObject("route", route);
		if (route.getId() == 0) {
			result.addObject("hikes", hikes);
		} else {
			result.addObject("hikes", routeHikes);
		}

		result.addObject("message", message);
		result.addObject("requestURI", "route/user/edit.do");

		return result;
	}

}