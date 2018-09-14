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
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CenterService;
import services.EmployeeService;
import domain.Center;
import domain.Employee;

@Controller
@RequestMapping("/center")
public class CenterController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CenterService petService;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private EmployeeService employeeService;

	// Constructors -----------------------------------------------------------

	public CenterController() {
		super();
	}

	// List ---------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Center> centers = new ArrayList<Center>();

		try {
			this.administratorService.findByPrincipal();

			centers = this.petService.findAll();
			for (Employee employee : this.employeeService.findByCenter(centers
					.iterator().next().getId())) {
				centers.remove(employee.getCenter());
			}
			System.out.println(centers);
			result = new ModelAndView("center/list");
			result.addObject("centers", centers);
			result.addObject("boss", false);
			result.addObject("requestURI", "center/list.do");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			centers = this.petService.findAll();

			result = new ModelAndView("center/list");
			result.addObject("centers", centers);
			result.addObject("boss", false);
			result.addObject("requestURI", "center/list.do");
		}

		return result;
	}

}
