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
import services.UserService;
import domain.Route;
import domain.User;

@Controller
@RequestMapping("/route")
public class RouteController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private RouteService routeService;
	
	@Autowired
	private UserService userService;

	// Constructors ---------------------------------------------------------

	public RouteController() {
		super();
	}

	// Registering ----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView searchList(@RequestParam(defaultValue = "0") int routeId) {
		ModelAndView res;
		Route route;

		if (routeId == 0) {
			res = new ModelAndView("redirect:../");

		} else if (this.routeService.findOne(routeId) == null) {
			res = new ModelAndView("redirect:../");
		} else {

			route = this.routeService.findOne(routeId);

			res = new ModelAndView("route/display");
			res.addObject("route", route);
			res.addObject("requestURI", "route/display.do");
		}
		return res;
	}

	@RequestMapping(value = "/lengthSearch", method = RequestMethod.GET)
	public ModelAndView lengthList(
			@RequestParam(defaultValue = "99999.0") String max,
			@RequestParam(defaultValue = "0.0") String min) {
		ModelAndView res = null;
		Collection<Route> routes;
		try {
			double maxValued = Double.valueOf(max);
			double minValued = Double.valueOf(min);
			int maxValue = (int) maxValued;
			int minValue = (int) minValued;
			routes = this.routeService.lengthRoute(maxValue, minValue);
			res = new ModelAndView("route/list");
			res.addObject("routes", routes);
			res.addObject("requestURI", "route/list.do");
		} catch (Exception e) {
			String message = "search.params.error";
			res = new ModelAndView("route/list");
			res.addObject("message", message);
			res.addObject("requestURI", "route/list.do");

		}

		return res;
	}

	@RequestMapping(value = "/hikesSearch", method = RequestMethod.GET)
	public ModelAndView hikesList(
			@RequestParam(defaultValue = "99999.") String max,
			@RequestParam(defaultValue = "0.") String min) {
		ModelAndView res = null;

		Collection<Route> routes;
		try {

			double maxValued = Double.valueOf(max);
			double minValued = Double.valueOf(min);
			int maxValue = (int) maxValued;
			int minValue = (int) minValued;

			routes = this.routeService.numHikesRoute(maxValue, minValue);
			res = new ModelAndView("route/list");
			res.addObject("routes", routes);
			res.addObject("requestURI", "route/list.do");

		} catch (Exception e) {
			String message = "search.params.error";
			res = new ModelAndView("route/list");
			res.addObject("message", message);
			res.addObject("requestURI", "route/list.do");

		}
		return res;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchList(@RequestParam(defaultValue = "") String criteria) {
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
		User principal;
		
		res = new ModelAndView("route/list");
		res.addObject("requestURI", "route/list.do");
		res.addObject("routes", routes);
		try{
			principal = this.userService.findByPrincipal();
			res.addObject("user",principal);
		}catch (Exception e) {
			res.addObject("user",null);
		}

		return res;
	}

}