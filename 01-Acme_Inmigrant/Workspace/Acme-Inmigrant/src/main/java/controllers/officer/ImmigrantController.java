package controllers.officer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ImmigrantService;
import controllers.AbstractController;
import domain.Application;
import domain.Immigrant;
import domain.Officer;

@Controller
@RequestMapping("/immigrant/officer")
public class ImmigrantController extends AbstractController{

	// Services
	
	private ImmigrantService immigrantService;
	
	// Supporting services

	private ActorService actorService;
	
	// Constructors
	
	public ImmigrantController(){
		super();
	}
	
	// Listing
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		
		Officer officer = (Officer) actorService.findByPrincipal();
		
		Collection<Application> applications = new ArrayList<Application>();
		Collection<Immigrant> all = new ArrayList<Immigrant>();
		Collection<Immigrant> withApplications = new ArrayList<Immigrant>();
		
		applications = officer.getApplications();
		all = immigrantService.findAll();

		for(Application a: applications){
			for(Immigrant i: all){
				if(a.getImmigrant() == i){
					withApplications.add(i);
				}
			}
		}
		
		res = new ModelAndView("immigrant/officer/list");
		res.addObject("immigrant", withApplications);
		res.addObject("requestURI", "immigrant/officer/list.do");
		
		return res;
	}
	
	// Editing
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int immigrantId){
		ModelAndView res;
		Immigrant immigrant;
		
		immigrant = immigrantService.findOne(immigrantId);
		res = createEditModelAndView(immigrant);
		res.addObject("immigrant", immigrant);
		
		return res;
	}
	
	// Ancillary methods --------------------------------------------------

		protected ModelAndView createEditModelAndView(final Immigrant immigrant) {
			ModelAndView result;

			result = this.createEditModelAndView(immigrant, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(final Immigrant immigrant,
				final String message) {
			ModelAndView result;

			result = new ModelAndView("immigrant/officer/edit");
			result.addObject("immigrant", immigrant);
			result.addObject("message", message);
			result.addObject("requestURI", "immigrant/officer/edit.do");

			return result;
		}
}
