package controllers.officer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.InvestigatorService;
import services.OfficerService;
import controllers.AbstractController;
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

	// Constructors ------------------
	public InvestigatorOfficerController() {
		super();
	}

	// Listing ------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		
		Collection<Application> application = new ArrayList<Application>();
		Collection<Immigrant> immigrant = new ArrayList<Immigrant>();
		List<Investigator> investigator = new ArrayList<Investigator>();
		
		Officer officer;
		officer = officerService.findByPrincipal();

		application = this.investigatorService.findApplicationByOfficer(officer.getId());

		for(Application a: application){
			immigrant.add(this.investigatorService.findImmigrantByApplication(a.getId()));
		}
		
		for(Immigrant i: immigrant){
			investigator.add(this.investigatorService.findInvestigatorByImmigrant(i.getId()));
		}
		
		for(int i = 0; i < 1; i++){
			if(investigator.get(i) == null){
				investigator = new ArrayList<Investigator>();
			}
		}
		
		res = new ModelAndView("investigator/officer/list");
		res.addObject("investigator", investigator);
		res.addObject("requestURI", "investigator/officer/list.do");

		return res;
	}
}
