package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Law;

import services.LawService;

@Controller
@RequestMapping("/law")
public class LawController extends AbstractController {

	@Autowired
	private LawService lawService;

	// constructor
	public LawController() {
		super();
	}

	// list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Law> law = new ArrayList<>();

		law = lawService.findAll();

		result = new ModelAndView("law/list");
		result.addObject("law", law);
		result.addObject("requestURI", "law/list.do");

		return result;
	}

	// Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int lawId) {
		ModelAndView res;
		Law law;

		law = this.lawService.findOne(lawId);

		res = new ModelAndView("law/display");
		res.addObject("id", law.getId());

		return res;
	}
}
