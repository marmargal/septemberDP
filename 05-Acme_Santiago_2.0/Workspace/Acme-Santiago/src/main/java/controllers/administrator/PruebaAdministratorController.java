package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PruebaService;
import controllers.AbstractController;
import domain.Prueba;

@Controller
@RequestMapping("/prueba/administrator")
public class PruebaAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private PruebaService pruebaService;
	
	// Constructors ---------------------------------------------------------

	public PruebaAdministratorController() {
		super();
	}


	// Listing --------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0") int routeId) {
		ModelAndView res;
		Collection<Prueba> pruebas = new ArrayList<>();

		pruebas = this.pruebaService.findPruebasByRouteAndWithoutDecision(routeId);
		res = new ModelAndView("prueba/list");
		res.addObject("requestURI", "prueba/administrator/list.do");
		res.addObject("pruebas", pruebas);

		return res;
	}
}
