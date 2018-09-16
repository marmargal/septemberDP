package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AmenityService;
import domain.Amenity;

@Controller
@RequestMapping("/amenity")
public class AmenityController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AmenityService amenityService;

	// Constructors ---------------------------------------------------------

	public AmenityController() {
		super();
	}
	
	//list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0") int innId) {
		ModelAndView res;
		Collection<Amenity> amenities = new ArrayList<Amenity>();
		
		amenities = this.amenityService.findAmenitiesByInn(innId);
		
		res = new ModelAndView("amenity/list");
		res.addObject("amenity", amenities);
		res.addObject("requestURI", "amenity/list.do");

		return res;
	}

	//display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(defaultValue = "0") int amenityId) {
		ModelAndView res;
		Amenity amenity = new Amenity();
		
		amenity = this.amenityService.findOne(amenityId);
		
		res = new ModelAndView("amenity/display");
		res.addObject("amenity", amenity);
		res.addObject("requestURI", "amenity/display.do");

		return res;
	}
}
