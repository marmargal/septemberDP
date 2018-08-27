package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AgentService;
import domain.Agent;
import forms.AgentForm;

@Controller
@RequestMapping("/agent")
public class RegisterAgentController extends AbstractController {

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
		Agent agent;
		agent = this.agentService.create();

		AgentForm agentForm;
		agentForm = new AgentForm(agent);

		res = new ModelAndView("agent/register");
		res.addObject("agentForm", agentForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("agentForm") AgentForm agentForm,
			final BindingResult binding) {
		ModelAndView res;
		agentForm = this.agentService.reconstruct(agentForm, binding);
		System.out.println(binding.getFieldError());
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(agentForm, "actor.params.error");
		} else {
			try {
				if ((agentForm.getAgent().getId() == 0)) {
					Assert.isTrue(agentForm.getAgent().getUserAccount()
							.getPassword()
							.equals(agentForm.getConfirmPassword()),
							"password does not match");
				}
				this.agentService.save(agentForm.getAgent());
				res = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("password does not match"))
					res = this.createEditModelAndView(agentForm,
							"actor.password.check");
				else if (oops
						.getMessage()
						.equals("could not execute statement; SQL [n/a]; constraint [null]"
								+ "; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"))
					res = this.createEditModelAndView(agentForm,
							"actor.commit.duplicate");
				else
					res = this.createEditModelAndView(agentForm,
							"actor.commit.error");
			}
		}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final AgentForm agentForm) {
		ModelAndView result;

		result = this.createEditModelAndView(agentForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AgentForm agentForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("agent/register");
		result.addObject("agent", agentForm);
		result.addObject("message", message);

		return result;
	}
}
