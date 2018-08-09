package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;

@Controller
@RequestMapping("/actor/administrator")
public class ActorAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private AdministratorService administratorService;



	// Constructors ---------------------------------------------------------

	public ActorAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Actor> actors;
		Collection<Administrator> administrators;

		actors = actorService.findAll();
		
		administrators = administratorService.findAll();
		
		actors.removeAll(administrators);

		result = new ModelAndView("actor/administrator");
		result.addObject("actors", actors);
		result.addObject("requestURI", "actor/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int actorId) {
		ModelAndView result;
		Actor actor;
		actor = this.actorService.findOne(actorId);
		this.actorService.ban(actor);
		result = this.createEditModelAndView(actor);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Actor actor) {
		ModelAndView result;

		result = this.createEditModelAndView(actor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Actor actor,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("administrator/ban");
		result.addObject("actor", actor);
		result.addObject("message", message);
		return result;
	}
}
