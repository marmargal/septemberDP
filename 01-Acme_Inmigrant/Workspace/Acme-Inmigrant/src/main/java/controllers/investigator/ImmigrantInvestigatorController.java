package controllers.investigator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ImmigrantService;
import services.InvestigatorService;
import controllers.AbstractController;
import domain.Immigrant;
import domain.Investigator;

@Controller
@RequestMapping("/immigrant/investigator")
public class ImmigrantInvestigatorController extends AbstractController {

	// Services

	@Autowired
	private ImmigrantService immigrantService;
	
	@Autowired
	private InvestigatorService investigatorService;

	// Constructors

	public ImmigrantInvestigatorController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		Collection<Immigrant> immigrants = new ArrayList<Immigrant>();
		
		Investigator investigator = investigatorService.findByPrincipal();

		immigrants = immigrantService.findImmigrantsByInvestigator(investigator.getId());

		res = new ModelAndView("immigrant/investigator/list");
		res.addObject("immigrant", immigrants);
		res.addObject("requestURI", "immigrant/investigator/list.do");

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

		result = new ModelAndView("immigrant/investigator/edit");
		result.addObject("immigrant", immigrant);
		result.addObject("message", message);
		result.addObject("requestURI", "immigrant/investigator/edit.do");

		return result;
	}
}
