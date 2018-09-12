/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PetService;
import controllers.AbstractController;
import domain.Pet;

@Controller
@RequestMapping("/pet/administrator")
public class PetAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private PetService petService;
	
	// Constructors -----------------------------------------------------------

	public PetAdministratorController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView listPending() {
		ModelAndView result;
	
		Collection<Pet> pets = new ArrayList<Pet>(); 
		pets = this.petService.findAll();
		
		result = new ModelAndView("pet/list");
		result.addObject("pets", pets);
		result.addObject("veterinaryPrincipal", null);
		result.addObject("viewForDelete" , true);
		result.addObject("requestURI", "pet/administrator/list.do");
	
		return result;
	}
	
	// Delete ---------------------------------------------------------------
	@RequestMapping(value="/delete",method=RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam(defaultValue = "0") int petId){
		ModelAndView res;
		try{
			Pet pet = this.petService.findOne(petId);
			this.petService.delete(pet);
			res = new ModelAndView("redirect:/pet/administrator/list.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		
		return res;
	}


}
