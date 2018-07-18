package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.LawService;
import controllers.AbstractController;
import domain.Law;
import domain.Visa;

@Controller
@RequestMapping("/law/administrator")
public class LawAdministratorController extends AbstractController{

	@Autowired
	private LawService lawService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Law> law = new ArrayList<>();
		law = lawService.findAll();
		result = new ModelAndView("law/administrator/list");
		result.addObject("requestURI", "law/administrator/list.do");
		result.addObject("law", law);
		return result;
	}
	
	
	
	protected ModelAndView createEditModelAndView(final Law law) {
		final ModelAndView result;
		result = this.createEditModelAndView(law, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final Law law,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("law/administrator/edit");
		result.addObject("law",law);
		result.addObject("message", message);
		result.addObject("requestUri", "law/administrator/edit.do");
		return result;
	}
	
}
