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

import services.CenterService;
import domain.Center;

@Controller
@RequestMapping("/center")
public class CenterController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private CenterService petService;
	
	// Constructors -----------------------------------------------------------

	public CenterController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Center> centers = new ArrayList<Center>(); 
		centers = this.petService.findAll();
		
		result = new ModelAndView("center/list");
		result.addObject("centers", centers);

		return result;
	}


}
