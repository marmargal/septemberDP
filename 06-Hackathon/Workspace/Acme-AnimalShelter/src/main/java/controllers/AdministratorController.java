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

import services.ActorService;
import services.BossService;
import services.ClientService;
import services.EmployeeService;
import services.VeterinaryService;
import services.VoluntaryService;
import domain.Actor;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}
	
	// Services ---------------------------------------------------------------
	@Autowired
	private ActorService actorService;


	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-1");

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-2");

		return result;
	}
	
	//list users --------------------------------------------------------------
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Actor> actors = new ArrayList<Actor>(); 
		actors = this.actorService.findAll();
		
		result = new ModelAndView("administrator/list");
		result.addObject("actors", actors);

		return result;
	}

}
