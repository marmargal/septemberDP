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
import services.ImmigrantService;
import services.SocialSectionService;
import controllers.AbstractController;
import domain.Application;
import domain.Immigrant;
import domain.SocialSection;

@Controller
@RequestMapping("/socialSection/immigrant")
public class SocialSectionImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private SocialSectionService socialSectionService;
	
	// Supporting services --------------------------------------------------
	
	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private ImmigrantService immigrantService;

	// Constructors ---------------------------------------------------------

	public SocialSectionImmigrantController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int socialSectionId) {
		ModelAndView result;
		SocialSection socialSection;

		socialSection = socialSectionService.findOne(socialSectionId);
		Assert.notNull(socialSection);
		result = this.createEditModelAndView(socialSection);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SocialSection socialSection,
			BindingResult binding) {
		ModelAndView res;
		
		System.out.println(binding.getFieldError());
		System.out.println(binding.getFieldErrors());
		
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(socialSection,
					"socialSection.params.error");
		} else
			try {
				this.socialSectionService.save(socialSection);
				res = new ModelAndView(
						"redirect:../../application/immigrant/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(socialSection,
						"socialSection.commit.error");
			}

		return res;
	}

	// Deleting -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(SocialSection socialSection, BindingResult binding) {
		ModelAndView res;

		try {
			this.socialSectionService.delete(socialSection);
			res = new ModelAndView(
					"redirect:../../application/immigrant/display.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(socialSection,
					"socialSection.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		SocialSection a;

		a = this.socialSectionService.create();
		result = this.createEditModelAndView(a);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final SocialSection socialSection) {
		ModelAndView result;
		result = this.createEditModelAndView(socialSection, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(
			final SocialSection socialSection, final String message) {

		ModelAndView result;
		Immigrant immigrant = immigrantService.findByPrincipal();
		Collection<Application> applications = new ArrayList<Application>();
		applications = applicationService.getApplicationByImmigrant(immigrant.getId());
		
		result = new ModelAndView("socialSection/immigrant/edit");
		result.addObject("socialSection", socialSection);
		result.addObject("application", applications);
		result.addObject("message", message);
		return result;
	}
}
