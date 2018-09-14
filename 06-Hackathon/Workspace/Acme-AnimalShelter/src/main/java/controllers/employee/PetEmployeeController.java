/*
 * EmployeeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.employee;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CenterService;
import services.EmployeeService;
import services.PetService;
import controllers.AbstractController;
import domain.Center;
import domain.Employee;
import domain.Pet;

@Controller
@RequestMapping("/pet/employee")
public class PetEmployeeController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private CenterService centerService;
	
	@Autowired
	private EmployeeService employeeService;
	
	// Constructors -----------------------------------------------------------

	public PetEmployeeController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView listPending() {
		ModelAndView result;
	
		Collection<Pet> pets = new ArrayList<Pet>(); 
		pets = this.petService.findAll();
		
		result = new ModelAndView("pet/list");
		result.addObject("veterinaryPrincipal", null);
		result.addObject("pets", pets);
		result.addObject("viewForDelete" , true);
		result.addObject("requestURI", "pet/employee/list.do");
	
		return result;
	}
	
	// Delete ---------------------------------------------------------------
	
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Pet pet, final BindingResult binding){
		ModelAndView res;
		try{
			this.petService.delete(pet);
			res = new ModelAndView("redirect:/pet/employee/list.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		
		return res;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Pet pet;

		pet = this.petService.create();
		res = this.createEditModelAndView(pet);

		return res;
	}
	
	// Saving --------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int petId) {
		ModelAndView result;
		Pet pet;
		if (petId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.petService.findOne(petId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			pet = this.petService.findOne(petId);
			result = this.createEditModelAndView(pet);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Pet pet, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(pet, "pet.params.error");
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.petService.save(pet);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this
						.createEditModelAndView(pet, "pet.commit.error");
			}
		return res;
	}
	
	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Pet pet) {
		ModelAndView result;

		result = this.createEditModelAndView(pet, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Pet pet,
			final String message) {
		ModelAndView result;
		Collection<Boolean> invalidate = new ArrayList<>();
		invalidate.add(false);
		invalidate.add(true);
		Center center = new Center();
		Collection<Center> centers= new ArrayList<Center>();
		Employee employee = this.employeeService.findByPrincipal();
		center = employee.getCenter();
		centers.add(center);
		
		result = new ModelAndView("pet/employee/edit");
		result.addObject("pet", pet);
		result.addObject("message", message);
		result.addObject("invalidate", invalidate);
		result.addObject("centers", centers);
		result.addObject("requestURI", "pet/employee/edit.do");
		return result;

	}

}
