package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChirpService;
import controllers.AbstractController;
import domain.Chirp;

@Controller
@RequestMapping("/chirp/administrator")
public class ChirpAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ChirpService chirpService;

	// Supporting services --------------------------------------------------

	// Constructors ---------------------------------------------------------

	public ChirpAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Chirp> chirps;

		chirpService.checkTabooWords();
		chirps = this.chirpService.findChirpTaboo();

		result = new ModelAndView("chirp/administrator/list");
		result.addObject("chirp", chirps);
		result.addObject("requestURI", "chirp/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		Collection<Chirp> chirps;
		Collection<Chirp> tabooChirps;

		chirps = chirpService.findAll();

		chirpService.checkTabooWords();
		tabooChirps = this.chirpService.findChirpTaboo();

		chirps.removeAll(tabooChirps);

		result = new ModelAndView("chirp/administrator/listAll");
		result.addObject("chirp", chirps);
		result.addObject("requestURI", "chirp/administrator/listAll.do");

		return result;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(defaultValue = "0") final int chirpId) {
		ModelAndView result;
		Chirp chirp;
		if (chirpId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.chirpService.findOne(chirpId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {

			chirp = this.chirpService.findOne(chirpId);
			result = this.createEditModelAndView(chirp);
			result.addObject("chirp", chirp);
		}
		return result;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Chirp chirp,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.chirpService.delete(chirp);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(chirp, "chirp.commit.error");
		}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Chirp chirp) {
		ModelAndView result;

		result = this.createEditModelAndView(chirp, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Chirp chirp,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("chirp/administrator/edit");
		result.addObject("chirp", chirp);
		result.addObject("message", message);
		result.addObject("requestURI", "chirp/administrator/edit.do");

		return result;
	}

}