package controllers.immigrant;

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
import services.WorkSectionService;
import controllers.AbstractController;
import domain.Application;
import domain.WorkSection;

@Controller
@RequestMapping("/workSection/immigrant")
public class WorkSectionImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private WorkSectionService workSectionService;
	
	// Supporting services --------------------------------------------------
	
	@Autowired
	private ApplicationService applicationService;

	// Constructors ---------------------------------------------------------

	public WorkSectionImmigrantController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int workSectionId) {
		ModelAndView result;
		WorkSection workSection;

		workSection = workSectionService.findOne(workSectionId);
		Assert.notNull(workSection);
		result = this.createEditModelAndView(workSection);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid WorkSection workSection,
			BindingResult binding) {
		ModelAndView res;
		
		System.out.println(binding.getFieldError());
		System.out.println(binding.getFieldErrors());
		
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(workSection,
					"workSection.params.error");
		} else
			try {
				this.workSectionService.save(workSection);
				res = new ModelAndView(
						"redirect:../../application/immigrant/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(workSection,
						"workSection.commit.error");
			}

		return res;
	}

	// Deleting -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(WorkSection workSection, BindingResult binding) {
		ModelAndView res;

		try {
			this.workSectionService.delete(workSection);
			res = new ModelAndView(
					"redirect:../../application/immigrant/display.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(workSection,
					"workSection.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int applicationId) {
		ModelAndView result;
		WorkSection a;
		Application application;
		
		application = this.applicationService.findOne(applicationId);
		a = this.workSectionService.create(application);
		result = this.createEditModelAndView(a);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final WorkSection workSection) {
		ModelAndView result;
		result = this.createEditModelAndView(workSection, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(
			final WorkSection workSection, final String message) {

		ModelAndView result;
		result = new ModelAndView("workSection/immigrant/edit");
		result.addObject("workSection", workSection);
		result.addObject("message", message);
		return result;
	}
}