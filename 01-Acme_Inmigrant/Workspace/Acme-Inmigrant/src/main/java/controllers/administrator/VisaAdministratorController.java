package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.VisaService;
import controllers.AbstractController;
import domain.Visa;

@Controller
@RequestMapping("/visa/administrator")
public class VisaAdministratorController extends AbstractController{

	// Services
	
	@Autowired
	private VisaService visaService;
	
	// Supporting services
	
	// Constructor
	
	public VisaAdministratorController(){
		super();
	}
	
	// Listing
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Visa> visa;
		
		visa = visaService.findAll();
		res = new ModelAndView("visa/administrator/list");
		res.addObject("visa", visa);
		res.addObject("requestURI", "visa/administrator/list.do");
		
		return res;
	}
}
