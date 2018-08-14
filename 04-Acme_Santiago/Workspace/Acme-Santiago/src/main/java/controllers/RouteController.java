package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RouteService;
import domain.Route;

@Controller
@RequestMapping("/route")
public class RouteController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private RouteService routeService;

	// Constructors ---------------------------------------------------------

	public RouteController() {
		super();
	}

	// Registering ----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView searchList(@RequestParam int routeId ) {
		ModelAndView res;
		Route route;

		route = this.routeService.findOne(routeId);

		res = new ModelAndView("route/display");
		res.addObject("route", route);
		res.addObject("requestURI", "route/display.do");
		return res;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchList(@RequestParam String criteria) {
		ModelAndView res;
		Collection<Route> routes;

		routes = this.routeService.searchRoute(criteria);

		res = new ModelAndView("route/list");
		res.addObject("routes", routes);
		res.addObject("requestURI", "route/list.do");
		return res;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Route> routes = new ArrayList<>();
		routes = this.routeService.findAll();
		res = new ModelAndView("route/list");
		res.addObject("requestURI", "routes/list.do");
		res.addObject("routes", routes);

		return res;
	}

}