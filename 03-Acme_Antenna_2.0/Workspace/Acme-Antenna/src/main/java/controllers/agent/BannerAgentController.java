package controllers.agent;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import controllers.AbstractController;
import domain.Banner;

@Controller
@RequestMapping("/banner/agent")
public class BannerAgentController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private BannerService bannerService;

	// Constructors ---------------------------------------------------------

	public BannerAgentController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Banner banner;

		banner = this.bannerService.create();
		res = this.createEditModelAndView(banner);

		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Banner banner,
			final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(banner,
					"banner.params.error");
		else
			try {
				this.bannerService.save(banner);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(banner,
						"banner.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Banner banner) {
		ModelAndView result;

		result = this.createEditModelAndView(banner, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Banner banner,
			final String message) {
		ModelAndView result;
		final Collection<Boolean> hide = new ArrayList<>();

		hide.add(false);
		hide.add(true);

		result = new ModelAndView("banner/agent/edit");
		result.addObject("banner", banner);
		result.addObject("message", message);
		result.addObject("requestURI", "banner/agent/edit.do");

		return result;
	}
}
