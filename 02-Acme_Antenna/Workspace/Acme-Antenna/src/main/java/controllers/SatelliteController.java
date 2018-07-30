package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SatelliteService;
import domain.Satellite;

@Controller
@RequestMapping("/satellite")
public class SatelliteController extends AbstractController{


	// Services -------------------------------------------------------------

	@Autowired
	private SatelliteService satelliteService;

	// Constructor

	public SatelliteController() {
		super();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchList(@RequestParam String criteria) {
		ModelAndView res;
		Collection<Satellite> satellites;

		satellites = this.satelliteService.searchSatellite(criteria);

		res = new ModelAndView("satellite/list");
		res.addObject("satellites", satellites);
		res.addObject("requestURI", "satellite/list.do");
		return res;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Satellite> satellites = new ArrayList<>();
		satellites = satelliteService.findAll();
		result = new ModelAndView("satellite/list");
		result.addObject("requestURI", "satellite/list.do");
		result.addObject("satellites", satellites);
		return result;
	}
}
