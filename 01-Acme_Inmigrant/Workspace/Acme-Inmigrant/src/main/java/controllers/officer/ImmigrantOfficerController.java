package controllers.officer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		Immigrant immigrant;

		immigrant = immigrantService.findOne(immigrantId);
		res = createEditModelAndView(immigrant);
		res.addObject("immigrant", immigrant);

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
		else
			try {
				Immigrant old = immigrantService.findOne(immigrant.getId());
				this.immigrantService.save(immigrant);
				if (immigrant.getInvestigator() != null) {
					// actualizo el nuevo
					Investigator investigator = immigrant.getInvestigator();
					Collection<Immigrant> immigrants = new ArrayList<>();
					// actulizo el viejo
					Investigator oldInvestigator = old.getInvestigator();
					Collection<Immigrant> oldImmigrants = oldInvestigator
							.getImmigrants();

					oldImmigrants.remove(immigrant);

					oldInvestigator.setImmigrants(oldImmigrants);

					this.investigatorService.save(oldInvestigator);
					immigrants.add(immigrant);
					investigator.setImmigrants(immigrants);

					this.investigatorService.save(investigator);

				} 
				res = new ModelAndView("redirect:../officer/list.do");

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(immigrant,
						"immigrant.commit.error");
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
