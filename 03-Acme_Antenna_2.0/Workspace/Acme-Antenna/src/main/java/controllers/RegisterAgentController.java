package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AgentService;
import domain.Agent;
import forms.ActorForm;

@Controller
@RequestMapping("/agent")
public class RegisterAgentController extends AbstractController{
	// Services -------------------------------------------------------------

	@Autowired
	private AgentService agentService;

	// Constructors ---------------------------------------------------------

	public RegisterAgentController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;

		ActorForm agentForm = new ActorForm();

		res = this.createEditModelAndView(agentForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm agentForm,
			final BindingResult binding) {
		ModelAndView res;
		Agent agent;

		if (binding.hasErrors())
			res = this.createEditModelAndView(agentForm,
					"actor.params.error");
		else if (!agentForm.getRepeatPassword().equals(
				agentForm.getPassword()))
			res = this.createEditModelAndView(agentForm,
					"actor.commit.errorPassword");
		else if (agentForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(agentForm,
					"actor.params.errorTerms");
		} else
			try {
				agent = agentService.reconstruct(agentForm,
						binding);
				this.agentService.save(agent);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(agentForm,
						"actor.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(
			final ActorForm agentForm) {
		ModelAndView result;

		result = this.createEditModelAndView(agentForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			final ActorForm agentForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("agent/register");
		result.addObject("actorForm", agentForm);
		result.addObject("message", message);
		result.addObject("requestURI","agent/register.do");

		return result;
	}
}
