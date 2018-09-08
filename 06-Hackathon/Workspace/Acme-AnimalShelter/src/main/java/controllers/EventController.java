/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.EventService;
import domain.Event;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private EventService petService;
	
	// Constructors -----------------------------------------------------------

	public EventController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Event> events = new ArrayList<Event>(); 
		events = this.petService.findEventNotEnd();
		
		result = new ModelAndView("event/list");
		result.addObject("events", events);
		result.addObject("requestURI", "event/list.do");

		return result;
	}


}
