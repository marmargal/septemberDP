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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CenterService;
import services.EmployeeService;
import controllers.AbstractController;
import domain.Center;
import domain.Employee;

@Controller
@RequestMapping("/center/administrator")
public class CenterAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CenterService centerService;

	@Autowired
	private EmployeeService employeeService;

	// Constructors -----------------------------------------------------------

	public CenterAdministratorController() {
		super();
	}

	// Delete ---------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam(defaultValue = "0") int centerId) {
		ModelAndView res;
		try {
			Center center = this.centerService.findOne(centerId);
			Boolean employee = false;
			Collection<Employee> employees = this.employeeService
					.findByCenter(center.getId());
			if (employees == null || employees.isEmpty()) {
				employee = true;
			}
			if (employee) {
				res = new ModelAndView("redirect:../../");
			}
			this.centerService.delete(center);
			res = new ModelAndView("redirect:/center/list.do");

		} catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}

		return res;
	}

}
