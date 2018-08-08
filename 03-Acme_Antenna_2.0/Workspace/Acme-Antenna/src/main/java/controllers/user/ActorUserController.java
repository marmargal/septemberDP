package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import controllers.AbstractController;
import domain.User;

@Controller
@RequestMapping("/actor/user")
public class ActorUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private UserService userService;

	// Constructors ---------------------------------------------------------

	public ActorUserController() {
		super();
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		User user;

		user = this.userService.findByPrincipal();
		result = this.createEditModelAndView(user);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid User user, final BindingResult binding) {
		ModelAndView res;
		user = this.userService.reconstruct(user, binding);
		if (binding.hasErrors())
			res = this.createEditModelAndView(user, "actor.params.error");
		else
			try {
				this.userService.save(user);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(user, "actor.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final User user) {
		ModelAndView result;

		result = this.createEditModelAndView(user, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final User user,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("actor/user/edit");
		result.addObject("actor", user);
		result.addObject("message", message);
		result.addObject("requestUri", "actor/user/edit.do");
		return result;

	}
}