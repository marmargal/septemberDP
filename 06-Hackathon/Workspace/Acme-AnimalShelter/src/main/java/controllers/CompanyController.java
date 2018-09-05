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

import services.CompanyService;
import domain.Company;

@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private CompanyService petService;
	
	// Constructors -----------------------------------------------------------

	public CompanyController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Company> companys = new ArrayList<Company>(); 
		companys = this.petService.findAll();
		
		result = new ModelAndView("company/list");
		result.addObject("companys", companys);
		result.addObject("requestURI", "company/list.do");

		return result;
	}


}
