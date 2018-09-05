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

import services.StandService;
import domain.Stand;

@Controller
@RequestMapping("/stand")
public class StandController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private StandService petService;
	
	// Constructors -----------------------------------------------------------

	public StandController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Stand> stands = new ArrayList<Stand>(); 
		stands = this.petService.findAll();
		
		result = new ModelAndView("stand/list");
		result.addObject("stands", stands);
		result.addObject("requestURI", "stand/list.do");

		return result;
	}


}
