package controllers.officer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.InvestigatorService;
import services.OfficerService;
import controllers.AbstractController;
import domain.Actor;
import domain.Application;
import domain.Immigrant;
import domain.Investigator;
import domain.Officer;

@Controller
@RequestMapping("/investigator/officer")
public class InvestigatorOfficerController extends AbstractController {

	// Services ----------------------
	
	@Autowired
	private InvestigatorService investigatorService;
	
	@Autowired
	private OfficerService officerService;

	@Autowired
	private ActorService actorService;
	
	// Constructors ------------------
	public InvestigatorOfficerController() {
		super();
	}

	// Listing ------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		
		Collection<Application> applicationOfOfficer = new ArrayList<Application>();
		Collection<Immigrant> immigrants = new ArrayList<Immigrant>();
		Collection<Investigator> investigators = new ArrayList<Investigator>();
		Officer officer = new Officer();
		Collection<Actor> investigatorActors = new ArrayList<Actor>();

		officer = officerService.findByPrincipal();

		applicationOfOfficer = officer.getApplications();

		for(Application a: applicationOfOfficer){
			immigrants.add(this.investigatorService.findImmigrantByApplication(a.getId()));
		}
		
		for(Immigrant i: immigrants){
				investigators.add(this.investigatorService.findInvestigatorByImmigrant(i.getId()));
		}
		
		for(Investigator i: investigators){
			if(i != null){
				investigatorActors.add(this.actorService.findByUserAccount(i.getUserAccount()));
			}
		}
		
		res = new ModelAndView("investigator/list");
		res.addObject("investigator", investigatorActors);
		res.addObject("requestURI", "investigator/officer/list.do");

		return res;
	}
}
