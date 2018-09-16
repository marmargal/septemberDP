package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CambioService;
import controllers.AbstractController;
import domain.Cambio;

@Controller
@RequestMapping("/cambio/administrator")
public class CambioAdministratorController extends AbstractController {

	@Autowired
	private CambioService cambioService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Cambio> cambios = new ArrayList<>();


		cambios = this.cambioService.cambiosWithoutDecision();
		res = new ModelAndView("cambio/administrator/list");
		res.addObject("requestUri", "cambio/administrator/list.do");
		res.addObject("cambios", cambios);

		return res;
	}

}
