package controllers.boss;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BossService;
import services.CenterService;
import services.EventService;
import controllers.AbstractController;
import domain.Center;
import domain.Event;

@Controller
@RequestMapping("/event/boss")
public class EventBossController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private EventService eventService;
	
	@Autowired
	private CenterService centerService;
	
	@Autowired
	private BossService bossService;

	// Constructors -----------------------------------------------------------

	public EventBossController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Event event;

		event = this.eventService.create();
		res = this.createEditModelAndView(event);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int eventId) {
		ModelAndView result;
		Event event;
		if (eventId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.eventService.findOne(eventId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			event = this.eventService.findOne(eventId);
			result = this.createEditModelAndView(event);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Event event, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(event, "event.params.error");
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.eventService.save(event);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this
						.createEditModelAndView(event, "event.commit.error");
			}
		return res;
	}

	// Delete ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Event event, final BindingResult binding) {
		ModelAndView res;
		try {
			this.eventService.delete(event);
			res = new ModelAndView("redirect:/event/list.do");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = new ModelAndView("redirect:../../");
		}

		return res;
	}
	

	protected ModelAndView createEditModelAndView(final Event event) {
		ModelAndView result;

		result = this.createEditModelAndView(event, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Event event,
			final String message) {
		ModelAndView result;
		Collection<Center> centers = new ArrayList<>();
		centers = this.bossService.findByPrincipal().getCenters();
		
		result = new ModelAndView("event/boss/edit");
		result.addObject("event", event);
		result.addObject("centers", centers);
		result.addObject("message", message);
		result.addObject("requestURI", "event/boss/edit.do");
		return result;

	}

}
