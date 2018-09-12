package controllers.agent;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AgentService;
import controllers.AbstractController;
import domain.Agent;

@Controller
@RequestMapping("/actor/agent")
public class ActorAgentController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AgentService agentService;

	// Constructors ---------------------------------------------------------

	public ActorAgentController() {
		super();
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Agent agent;

		agent = this.agentService.findByPrincipal();
		result = this.createEditModelAndView(agent);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Agent agent, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(agent, "actor.params.error");
		else
			try {
				this.agentService.save(agent);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(agent, "actor.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Agent agent) {
		ModelAndView result;

		result = this.createEditModelAndView(agent, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Agent agent,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("actor/agent/edit");
		result.addObject("actor", agent);
		result.addObject("message", message);
		result.addObject("requestURI", "actor/agent/edit.do");
		return result;

	}
}