package controllers.user;

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

import services.InnService;
import services.RouteService;
import services.UserService;
import services.WalkService;
import controllers.AbstractController;
import domain.Inn;
import domain.User;
import domain.Walk;

@Controller
@RequestMapping("/walk/user")
public class WalkUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private WalkService walkService;

	@Autowired
	private UserService userService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private InnService innService;
	
	// Constructors ---------------------------------------------------------

	public WalkUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Walk> walks = new ArrayList<>();

		User user = userService.findByPrincipal();

		walks = this.walkService.findWalkByUser(user.getId());
		res = new ModelAndView("walk/user/list");
		res.addObject("requestURI", "walk/user/list.do");
		res.addObject("walks", walks);

		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(defaultValue = "0") int routeId) {
		ModelAndView res;
		Walk walk;
		walk = this.walkService.create(routeService.findOne(routeId));
		res = this.createEditModelAndView(walk);
		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Walk walk, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(walk, "walk.params.error");
		else
			try {
				this.walkService.save(walk);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(walk, "walk.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Walk walk) {
		ModelAndView result;

		result = this.createEditModelAndView(walk, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Walk walk,
			final String message) {
		ModelAndView result;
		
		Collection<Inn> inns = new ArrayList<>();
		inns.addAll(innService.findAll());

		result = new ModelAndView("walk/user/edit");
		result.addObject("walk", walk);
		result.addObject("inns", inns);
		result.addObject("message", message);
		result.addObject("requestURI", "walk/user/edit.do");

		return result;
	}
}
