package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.HandyworkerService;
import domain.Handyworker;

@Controller
@RequestMapping("/handyworker")
public class HandyworkerController extends AbstractController{

	// Services -------------------------------------------------------------

	@Autowired
	private HandyworkerService handyworkerService;

	// Constructor

	public HandyworkerController() {
		super();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Handyworker> handyworkers = new ArrayList<>();
		handyworkers = handyworkerService.findAll();
		result = new ModelAndView("handyworker/list");
		result.addObject("requestURI", "handyworker/list.do");
		result.addObject("handyworkers", handyworkers);
		return result;
	}
}
