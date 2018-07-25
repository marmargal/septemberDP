package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.VisaService;
import domain.Visa;

@Controller
@RequestMapping("/visa")
public class VisaController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private VisaService visaService;

	// Constructor

	public VisaController() {
		super();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchList(@RequestParam String criteria) {
		ModelAndView res;
		Collection<Visa> visas;

		visas = this.visaService.searchVisa(criteria);

		res = new ModelAndView("visa/list");
		res.addObject("visas", visas);
		res.addObject("requestURI", "visa/list.do");
		return res;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Visa> visas = new ArrayList<>();
		visas = visaService.findAll();
		result = new ModelAndView("visa/list");
		result.addObject("requestURI", "visa/list.do");
		result.addObject("visas", visas);
		return result;
	}
	
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int categoryId) {
		ModelAndView result;
		Collection<Visa> visas = new ArrayList<>();
		visas = visaService.findVisasByCategory(categoryId);
		result = new ModelAndView("visa/list");
		result.addObject("requestURI", "visa/category/list.do");
		result.addObject("visas", visas);
		return result;
	}
	
	@RequestMapping(value = "/country/list", method = RequestMethod.GET)
	public ModelAndView listByCountry(@RequestParam int countryId) {
		ModelAndView result;
		Collection<Visa> visas = new ArrayList<>();
		visas = visaService.findVisasByCountry(countryId);
		result = new ModelAndView("visa/list");
		result.addObject("requestURI", "visa/country/list.do");
		result.addObject("visas", visas);
		return result;
	}
	
	// Display
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam final int visaId){
		ModelAndView res;
		Visa visa;
	
		visa = this.visaService.findOne(visaId);
		
		res = new ModelAndView("visa/display");
		res.addObject("visa",visa);
		
		return res;
	}
}
