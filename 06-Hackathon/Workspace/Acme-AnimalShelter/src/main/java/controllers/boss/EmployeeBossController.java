/*
 * EmployeeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.boss;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CenterService;
import services.EmployeeService;
import controllers.AbstractController;
import domain.Center;
import domain.Employee;
import forms.ActorForm;

@Controller
@RequestMapping("/employee/boss")
public class EmployeeBossController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CenterService centerService;
	
	
	// Constructors -----------------------------------------------------------

	public EmployeeBossController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		ActorForm clientForm = new ActorForm();
		
		res = this.createEditModelAndView(clientForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm employeeForm, final BindingResult binding) {
		ModelAndView res;
		Employee employee;

		if (binding.hasErrors())
			res = this.createEditModelAndView(employeeForm, "actor.params.error");
		else if (!employeeForm.getRepeatPassword().equals(employeeForm.getPassword()))
			res = this.createEditModelAndView(employeeForm, "actor.commit.errorPassword");
		else if (employeeForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(employeeForm, "actor.params.errorTerms");
		} else
			try {
				employee = employeeService.reconstruct(employeeForm, binding);
				this.employeeService.save(employee);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(employeeForm, "actor.commit.error");
			}

		return res;
	}
	
	protected ModelAndView createEditModelAndView(final ActorForm employeeForm) {
		ModelAndView result;

		result = this.createEditModelAndView(employeeForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm employeeForm,
			final String message) {
		ModelAndView result;
		Collection<Center> centers = new ArrayList<Center>();
		centers = this.centerService.findAll();

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", employeeForm);
		result.addObject("centers", centers);
		result.addObject("message", message);
		result.addObject("requestURI","employee/boss/register.do");

		return result;
	}

}
