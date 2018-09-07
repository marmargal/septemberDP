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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BossService;
import services.EmployeeService;
import controllers.AbstractController;
import domain.Boss;
import domain.Center;
import domain.Employee;
import forms.ActorForm;
import forms.AssignEmployeeForm;

@Controller
@RequestMapping("/employee/boss")
public class EmployeeBossController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private BossService bossService;
	
	
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
		
		Boss boss = this.bossService.findByPrincipal();
		Collection<Center> centers = new ArrayList<Center>();
		centers = boss.getCenters();

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", employeeForm);
		result.addObject("centers", centers);
		result.addObject("message", message);
		result.addObject("requestURI","employee/boss/register.do");

		return result;
	}
	
	// Assign ----------------------------------------------------------
	
	@RequestMapping("/list")
	public ModelAndView listNonDescarted() {
		ModelAndView result;

		Collection<Employee> employees = new ArrayList<Employee>(); 
		employees = this.employeeService.findEmployeesByCentersBoss();
					
		result = new ModelAndView("actor/list");
		result.addObject("actors", employees);
		result.addObject("viewForAssign", true);

		return result;
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.GET)
	public ModelAndView assign(@RequestParam(defaultValue = "0") final int employeeId) {
		ModelAndView res;

		Employee employee = this.employeeService.findOne(employeeId);
		AssignEmployeeForm assignEmployeeForm = new AssignEmployeeForm();
		assignEmployeeForm.setEmployeeId(employeeId);
		assignEmployeeForm.setCenter(employee.getCenter());
		
		res = this.createEditModelAndView(assignEmployeeForm);

		return res;
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.POST, params = "save")
	public ModelAndView assign(@Valid final AssignEmployeeForm assignEmployeeForm, final BindingResult binding) {
		ModelAndView res;
		Employee employee;

		if (binding.hasErrors()){
			res = this.createEditModelAndView(assignEmployeeForm, "employee.params.error");
		} else
			try {
				employee = employeeService.reconstruct(assignEmployeeForm, binding);
				this.employeeService.save(employee);
				res = new ModelAndView("redirect:/employee/boss/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(assignEmployeeForm, "employee.commit.error");
			}
		return res;
	}
	
	protected ModelAndView createEditModelAndView(final AssignEmployeeForm assignEmployeeForm) {
		ModelAndView result;

		result = this.createEditModelAndView(assignEmployeeForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AssignEmployeeForm assignEmployeeForm,
			final String message) {
		ModelAndView result;
		Boss boss = this.bossService.findByPrincipal();
		Collection<Center> centers = boss.getCenters();

		result = new ModelAndView("employee/assign");
		result.addObject("assignEmployeeForm", assignEmployeeForm);
		result.addObject("centers", centers);
		result.addObject("message", message);
		result.addObject("requestURI","employee/boss/assign.do");

		return result;
	}

}
