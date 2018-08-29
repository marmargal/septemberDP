package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections.map.HashedMap;
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
import domain.Route;

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
		Map<Hike, Registry> map = new HashedMap();
		ArrayList<Hike> hikesWithoutRegistry = new ArrayList<>();
		hikesWithoutRegistry.addAll(hikes);
		hikesWithoutRegistry.removeAll(hikesRegistries);
		for (Registry registry : registries) {
			if (hikes.contains(registry.getHike())) {
				hikesRegistries.add(registry.getHike());
				map.put(registry.getHike(), registry);
			}
		}
		if (!hikesWithoutRegistry.isEmpty()) {
			for (Hike hike : hikesWithoutRegistry) {
				map.put(hike, null);
			}
		}

		boolean test = hikesRegistries.containsAll(hikes);
		ArrayList<Date> dates = new ArrayList<>();
		dates.addAll(compostela.getWalk().getDaysOfEachHike());
		boolean days = true;
		if (dates == null || dates.isEmpty()) {
			days = false;

		} else if (dates.size() > 1) {
			for (int i = 1; i < dates.size(); i++) {
				if (dates.get(i).getYear() != dates.get(i - 1).getYear()) {
					days = false;
					break;
				} else if (dates.get(i).getMonth()
						- dates.get(i - 1).getMonth() != 1) {
					days = false;
					break;
				} else if (dates.get(i).getDay() - dates.get(i - 1).getDay() != 1) {
					days = false;
					break;
				}
			}
		}

		result = new ModelAndView("compostela/administrator/edit");
		result.addObject("compostela", compostela);
		Route route = compostela.getWalk().getRoute();
		result.addObject("route", route);

		result.addObject("map", map);
		result.addObject("days", days);
		result.addObject("test", test);
		result.addObject("message", message);
		result.addObject("requestURI", "compostela/administrator/edit.do");

		return result;
	}
}
