package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LawService;
import services.RequirementService;
import controllers.AbstractController;
import domain.Law;
import domain.Requirement;
import forms.LawForm;

@Controller
@RequestMapping("/law/administrator")
public class LawAdministratorController extends AbstractController {

	@Autowired
	private LawService lawService;
	
	@Autowired
	private RequirementService requirementService;

	// Constructors ---------------------------------------------------------

	public LawAdministratorController() {
		super();
	}

	// Create --------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		LawForm lawForm = new LawForm();

		res = this.createEditModelAndView(lawForm);

		return res;
	}

	// Edit--------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int lawId) {

		ModelAndView res;

		Law law = lawService.findOne(lawId);
		LawForm lawForm = lawService.construct(law);

		res = createEditModelAndView(lawForm);
		res.addObject("lawForm", lawForm);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final LawForm lawForm, final BindingResult binding) {
		ModelAndView res;
		Set<Requirement> requirement = new HashSet<Requirement>();
		requirement.addAll(lawForm.getRequirement());
		List<Requirement> newList=new ArrayList<Requirement>();
		newList.addAll(requirement);
		requirement.clear();
		lawForm.setRequirement( newList);
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(lawForm, "law.params.error");
			
		} else
			try {

				Law law = this.lawService.reconstruct(lawForm, binding);
				this.lawService.save(law);

				res = new ModelAndView("redirect:/law/list.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				System.out.println(lawForm.getRequirement());
				res = this.createEditModelAndView(lawForm, "law.commit.error");
			}

		return res;
	}

	// Delete --------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(LawForm lawForm, BindingResult binding) {

		ModelAndView res;

		try {
			Law law = this.lawService.reconstruct(lawForm, binding);
			lawService.delete(law);
			res = new ModelAndView("redirect:/law/list.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(lawForm, "law.commit.error");
		}

		return res;
	}

	protected ModelAndView createEditModelAndView(final LawForm law) {
		final ModelAndView result;
		result = this.createEditModelAndView(law, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final LawForm lawForm,
			final String message) {
		final ModelAndView result;
		Collection<Law> laws = this.lawService.findAll();
		Collection<Requirement> requirement = this.requirementService.findAll();

		result = new ModelAndView("law/administrator/edit");
		result.addObject("requirement", requirement);
		result.addObject("lawParent", laws);
		result.addObject("lawForm", lawForm);
		result.addObject("message", message);
		result.addObject("requestUri", "law/administrator/edit.do");
		return result;
	}

}
