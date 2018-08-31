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

import services.PetService;
import domain.Pet;

@Controller
@RequestMapping("/pet")
public class PetController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private PetService petService;
	
	// Constructors -----------------------------------------------------------

	public PetController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/listNotAdopted")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Pet> pets = new ArrayList<Pet>(); 
		pets = this.petService.findPetsNotAdopted();
		
		result = new ModelAndView("pet/list");
		result.addObject("pets", pets);

		return result;
	}


}
