package controllers.immigrant;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.ContactSectionService;
import services.ImmigrantService;
import controllers.AbstractController;
import domain.Application;
import domain.ContactSection;
import domain.Immigrant;

@Controller
@RequestMapping("/contactSection/immigrant")
public class ContactSectionImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ContactSectionService contactSectionService;
	
	// Supporting services --------------------------------------------------
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ImmigrantService immigrantService;

	// Constructors ---------------------------------------------------------

	public ContactSectionImmigrantController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int contactSectionId) {
		ModelAndView result;
		ContactSection contactSection;

		contactSection = contactSectionService.findOne(contactSectionId);
		Assert.notNull(contactSection);
		result = this.createEditModelAndView(contactSection);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ContactSection contactSection,
			BindingResult binding) {
		ModelAndView res;
		
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(contactSection,
					"contactSection.params.error");
		} else
			try {
				this.contactSectionService.save(contactSection);
				res = new ModelAndView(
						"redirect:../../application/immigrant/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(contactSection,
						"contactSection.commit.error");
			}

		return res;
	}

	// Deleting -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(ContactSection contactSection, BindingResult binding) {
		ModelAndView res;

		try {
			this.contactSectionService.delete(contactSection);
			res = new ModelAndView(
					"redirect:../../application/immigrant/display.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(contactSection,
					"contactSection.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ContactSection a;
		
		a = this.contactSectionService.create();
		result = this.createEditModelAndView(a);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final ContactSection contactSection) {
		ModelAndView result;
		result = this.createEditModelAndView(contactSection, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(
			final ContactSection contactSection, final String message) {

		ModelAndView result;
		Immigrant immigrant = immigrantService.findByPrincipal();
		Collection<Application> applications = new ArrayList<Application>();
		applications = applicationService.getApplicationByImmigrant(immigrant.getId());
		
		result = new ModelAndView("contactSection/immigrant/edit");
		result.addObject("contactSection", contactSection);
		result.addObject("application", applications);
		result.addObject("message", message);
		return result;
	}
}
