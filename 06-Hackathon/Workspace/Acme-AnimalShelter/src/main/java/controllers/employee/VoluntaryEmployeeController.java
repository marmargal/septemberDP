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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EmployeeService;
import services.StandService;
import services.VoluntaryService;
import controllers.AbstractController;
import domain.Employee;
import domain.Stand;
import domain.Voluntary;

@Controller
@RequestMapping("/voluntary/employee")
public class VoluntaryEmployeeController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private VoluntaryService voluntaryService;
	
	@Autowired
	private StandService standService;
	
	@Autowired
	private EmployeeService employeeService;
	
	// Constructors -----------------------------------------------------------

	public VoluntaryEmployeeController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/listByStand")
	public ModelAndView list() {
		ModelAndView result;
	
		Collection<Voluntary> voluntaries = new ArrayList<Voluntary>(); 
		voluntaries = this.voluntaryService.findByStandEmployee();
		
		result = new ModelAndView("actor/list");
		result.addObject("actors", voluntaries);
	
		return result;
	}
	
	// Untie ---------------------------------------------------------------
	@RequestMapping(value="/untie",method=RequestMethod.POST, params = "untie")
	public ModelAndView untie(@RequestParam(defaultValue = "0") int voluntaryId){
		ModelAndView res;
		try{
			Employee employee = this.employeeService.findByPrincipal();
			Stand stand = employee.getStand();
			this.standService.untieVoluntary(stand);
			
			res = new ModelAndView("redirect:/voluntary/employee/listByStand.do");
		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		
		return res;
	}

}
