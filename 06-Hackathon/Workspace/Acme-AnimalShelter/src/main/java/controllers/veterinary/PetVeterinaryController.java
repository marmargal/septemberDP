/*
 * VeterinaryController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.veterinary;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.PetService;
import services.VeterinaryService;
import controllers.AbstractController;
import domain.Pet;
import domain.Veterinary;

@Controller
@RequestMapping("/pet/veterinary")
public class PetVeterinaryController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private VeterinaryService veterinaryService;
	
	// Constructors -----------------------------------------------------------

	public PetVeterinaryController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
	
		Veterinary veterinary;
		Collection<Pet> pets = new ArrayList<Pet>(); 
		pets = this.petService.findAll();
		
		result = new ModelAndView("pet/list");
		try{
			veterinary = this.veterinaryService.findByPrincipal();
			result.addObject("veterinaryPrincipal", veterinary);
		}catch (Exception e) {
			result.addObject("veterinaryPrincipal", null);
		}
		result.addObject("pets", pets);
		result.addObject("viewForDelete" , true);
		result.addObject("requestURI", "pet/veterinary/list.do");
	
		return result;
	}
	
}
