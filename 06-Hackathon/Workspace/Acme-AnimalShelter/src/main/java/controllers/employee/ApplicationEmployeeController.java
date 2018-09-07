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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.EmployeeService;
import controllers.AbstractController;
import domain.Application;

@Controller
@RequestMapping("/application/employee")
public class ApplicationEmployeeController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private EmployeeService employeeService;

	// Constructors -----------------------------------------------------------

	public ApplicationEmployeeController() {
		super();
	}

	// List ---------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Application> applications = this.applicationService
				.findApplicationsPendingPerCentre(employeeService
						.findByPrincipal().getCenter());

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/employee/list.do");
		return result;
	}

}
