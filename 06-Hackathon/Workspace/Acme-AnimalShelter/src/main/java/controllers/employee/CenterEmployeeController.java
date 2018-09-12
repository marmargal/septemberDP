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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CenterService;
import controllers.AbstractController;
import domain.Center;

@Controller
@RequestMapping("/center/employee")
public class CenterEmployeeController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private CenterService centerService;
	
	// Constructors -----------------------------------------------------------

	public CenterEmployeeController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(defaultValue = "0") int petId) {
		ModelAndView result;
	
		Center center = new Center(); 
		center = this.centerService.findByPet(petId);
		
		result = new ModelAndView("center/list");
		result.addObject("centers", center);
		result.addObject("requestURI", "center/employee/list.do");
	
		return result;
	}
	


}
