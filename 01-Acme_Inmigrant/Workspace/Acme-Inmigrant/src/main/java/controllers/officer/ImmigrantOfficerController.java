package controllers.officer;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ImmigrantService;
import services.InvestigatorService;
import services.OfficerService;
import controllers.AbstractController;
import domain.Application;
import domain.Immigrant;
import domain.Investigator;
import domain.Officer;

@Controller
@RequestMapping("/immigrant/officer")
public class ImmigrantOfficerController extends AbstractController {

	// Services

	@Autowired
	private ImmigrantService immigrantService;

	// Supporting services

	@Autowired
	private OfficerService officerService;

	@Autowired
	private InvestigatorService investigatorService;

	// Constructors

	public ImmigrantOfficerController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		Officer officer = new Officer();
		officer = officerService.findByPrincipal();

		Collection<Application> applications = new ArrayList<Application>();
		Collection<Immigrant> all = new ArrayList<Immigrant>();
		Collection<Immigrant> withApplications = new ArrayList<Immigrant>();

		applications = officer.getApplications();
		all = immigrantService.findAll();

		for (Application a : applications) {
			for (Immigrant i : all) {
				if (a.getImmigrant() == i) {
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
	public ModelAndView edit(@RequestParam final int immigrantId) {
		ModelAndView res;
		Immigrant immigrant = immigrantService.findOne(immigrantId);
		Officer officer = officerService.findByPrincipal();
		
		// si el immigrant pertenece a alguna application del officer
		int count = 0;
		for(Application application : immigrant.getApplications()){
			if(officer.getApplications().contains(application)){
				count ++;
			}
		}
		
		if((immigrant.getInvestigator()==null) && (count!=0)){
			res = createEditModelAndView(immigrant);
			res.addObject("immigrant", immigrant);
		}else{
			res = new ModelAndView("redirect:../../");
		}

		return res;
	}

	// Saving

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Immigrant immigrant,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(immigrant,
					"immigrant.params.error");
		else{
			try {
				this.immigrantService.assignNewInvestigator(immigrant);
				res = new ModelAndView("redirect:../officer/list.do");

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(immigrant,
						"immigrant.commit.error");
			}
		}
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

		Collection<Investigator> investigator = new ArrayList<Investigator>();
		investigator = investigatorService.findAll();

		result = new ModelAndView("immigrant/officer/edit");
		result.addObject("immigrant", immigrant);
		result.addObject("investigator", investigator);
		result.addObject("message", message);
		result.addObject("requestURI", "immigrant/officer/edit.do");

		return result;
	}
}
