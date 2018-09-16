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

import services.AdministratorService;
import services.CambioService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Cambio;

@Controller
@RequestMapping("/cambio/administrator")
public class CambioAdministratorController extends AbstractController {

	@Autowired
	private CambioService cambioService;
	
	@Autowired
	private AdministratorService administratorService;
	
	// Constructors ---------------------------------------------------------

	public CambioAdministratorController() {
		super();
	}
	
	// Listing ---------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Cambio> cambios = new ArrayList<Cambio>();
		
		Administrator admin = this.administratorService.findByPrincipal();
		
		cambios = this.cambioService.findCambiosWithoutDecision();
		
		res = new ModelAndView("cambio/administrator/list");
		res.addObject("cambios", cambios);
		res.addObject("admin", admin);
		res.addObject("requestURI", "cambio/administrator/list.do");

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int cambioId) {
		ModelAndView result;
		Cambio cambio;

		if (cambioId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.cambioService.findOne(cambioId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {

			cambio = this.cambioService.findOne(cambioId);
			result = this.createEditModelAndView(cambio);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Cambio cambio, final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(cambio, "cambio.params.error");
		else
			try {
				this.cambioService.save(cambio);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this
						.createEditModelAndView(cambio, "cambio.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	private ModelAndView createEditModelAndView(final Cambio cambio) {
		ModelAndView result;

		result = this.createEditModelAndView(cambio, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final Cambio cambio,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("cambio/administrator/edit");
		result.addObject("cambio", cambio);
		result.addObject("message", message);
		result.addObject("requestURI", "cambio/administrator/edit.do");

		return result;
	}
}
