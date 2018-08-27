package controllers.administrator;

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

import services.CompostelaService;
import services.RegistrytService;
import controllers.AbstractController;
import domain.Compostela;
import domain.Hike;
import domain.Registry;

@Controller
@RequestMapping("/compostela/administrator")
public class CompostelaAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CompostelaService compostelaService;

	@Autowired
	private RegistrytService registryService;

	// Constructors ---------------------------------------------------------

	public CompostelaAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Compostela> compostelas = new ArrayList<>();

		compostelas.addAll(this.compostelaService
				.findCompostelaByFinallyDecision(false));

		res = new ModelAndView("compostela/administrator/list");
		res.addObject("requestURI", "compostela/administrator/list.do");
		res.addObject("compostelas", compostelas);

		return res;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int compostelaId) {
		ModelAndView result;
		Compostela compostela;

		if (compostelaId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.compostelaService.findOne(compostelaId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			compostela = this.compostelaService.findOne(compostelaId);
			result = this.createEditModelAndView(compostela);
		}
		return result;
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
		Collection<Hike> hikes = compostela.getWalk().getRoute().getHikes();
		Collection<Registry> registries = this.registryService
				.findByUser(compostela.getUser());
		Collection<Hike> hikesRegistries = new ArrayList<>();
		for (Registry registry : registries) {
			hikesRegistries.add(registry.getHike());
		}
		boolean test = hikesRegistries.containsAll(hikes);
		result = new ModelAndView("compostela/administrator/edit");
		result.addObject("compostela", compostela);
		
		result.addObject("test", test);
		result.addObject("message", message);
		result.addObject("requestURI", "compostela/administrator/edit.do");

		return result;
	}
}
