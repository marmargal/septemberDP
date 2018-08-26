package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CompostelaService;
import services.UserService;
import services.WalkService;
import controllers.AbstractController;
import domain.Compostela;
import domain.User;
import domain.Walk;

@Controller
@RequestMapping("/compostela/user")
public class CompostelaUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CompostelaService compostelaService;

	@Autowired
	private WalkService walkService;

	@Autowired
	private UserService userService;
	
	// Constructors ---------------------------------------------------------

	public CompostelaUserController() {
		super();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(defaultValue = "0") int compostelaId) {
		ModelAndView res;
		Compostela compostela;

		if (compostelaId == 0) {
			res = new ModelAndView("redirect:../");

		} else if (this.compostelaService.findOne(compostelaId) == null) {
			res = new ModelAndView("redirect:../");
		} else {

			compostela = this.compostelaService.findOne(compostelaId);

			User user = userService.findUserByCompostela(compostela);
			
			res = new ModelAndView("compostela/display");
			res.addObject("compostela", compostela);
			res.addObject("user", user);
			res.addObject("requestURI", "compostela/display.do");
		}
		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(
			@RequestParam(defaultValue = "0") final int walkId) {
		ModelAndView res;
		Compostela compostela;
		compostela = this.compostelaService.create();

		Walk walk = walkService.findOne(walkId);
		compostela.setWalk(walk);

		res = this.createEditModelAndView(compostela);
		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Compostela compostela,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(compostela,
					"compostela.params.error");
		else
			try {
				this.compostelaService.save(compostela);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(compostela,
						"compostela.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Compostela compostela) {
		ModelAndView result;

		result = this.createEditModelAndView(compostela, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Compostela compostela,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("compostela/user/edit");
		result.addObject("compostela", compostela);
		result.addObject("message", message);
		result.addObject("requestURI", "compostela/user/edit.do");

		return result;
	}
}
