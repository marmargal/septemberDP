package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CompostelaService;
import controllers.AbstractController;
import domain.Compostela;

@Controller
@RequestMapping("/compostela/administrator")
public class CompostelaAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CompostelaService compostelaService;

	// Constructors ---------------------------------------------------------

	public CompostelaAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Compostela> compostelas = new ArrayList<>();
		
		compostelas.addAll(this.compostelaService.findCompostelaByDecision(false));

		res = new ModelAndView("compostela/administrator/list");
		res.addObject("requestURI", "compostela/administrator/list.do");
		res.addObject("compostelas", compostelas);

		return res;
	}
}
