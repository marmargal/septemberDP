package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CountryService;
import domain.Country;

@Controller
@RequestMapping("/country")

public class CountryController extends AbstractController {
	
	// Services -------------------------------------------------------------

	@Autowired
	private CountryService countryService;

	// Constructors ---------------------------------------------------------

	public CountryController() {
		super();
	}

	// Listing ---------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		Collection<Country> countries = new ArrayList<Country>();
		
		countries = this.countryService.findAll();
		
		res = new ModelAndView("country/list");
		res.addObject("countries", countries);

		return res;
	}

	// Display ---------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int countryId) {
		ModelAndView result;
		
		Country country = this.countryService.findOne(countryId);

		result = new ModelAndView("country/display");
		result.addObject("country", country);
		
		return result;
	}
	
	
}
