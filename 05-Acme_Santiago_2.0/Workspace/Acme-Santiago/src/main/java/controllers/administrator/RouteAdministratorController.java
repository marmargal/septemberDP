package controllers.administrator;

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
import domain.Route;

@Controller
@RequestMapping("/route/administrator")
public class RouteAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private RouteService routeService;

	// Supporting services --------------------------------------------------

	// Constructors ---------------------------------------------------------

	public RouteAdministratorController() {
		super();
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
			result = this.createEditModelAndView(route);
			result.addObject("route", route);
		}
		return result;
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

		result = new ModelAndView("route/administrator/edit");
		result.addObject("route", route);
		result.addObject("message", message);
		result.addObject("requestURI", "route/administrator/edit.do");

		return result;
	}

}