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

import services.ApplicationService;
import services.EmployeeService;
import services.ReportService;
import controllers.AbstractController;
import domain.Application;
import domain.Report;

@Controller
@RequestMapping("/report/employee")
public class ReportEmployeeController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ReportService reportService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ApplicationService applicationService;

	// Constructors -----------------------------------------------------------

	public ReportEmployeeController() {
		super();
	}

	// List ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(
			@RequestParam(defaultValue = "0") int applicationId) {
		ModelAndView res;
		Report report;

		report = this.reportService.create();
		if (applicationId == 0
				|| this.applicationService.findOne(applicationId) == null) {
			res = new ModelAndView("redirect:../../");
		} else {
			report.setApplication(this.applicationService
					.findOne(applicationId));
			res = this.createEditModelAndView(report);
		}

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Report report, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(report, "report.params.error");
			System.out.println(binding.getAllErrors());
		} else
			try {
				report.setEmployee(employeeService.findByPrincipal());
				Application application = report.getApplication();
				Report saved = this.reportService.save(report);
				application.setReport(saved);

				applicationService.save(application);

				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this
						.createEditModelAndView(report, "report.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final Report report) {
		ModelAndView result;

		result = this.createEditModelAndView(report, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Report report,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("report/employee/create");
		result.addObject("report", report);
		Collection<Boolean> suitables = new ArrayList<>();
		suitables.add(true);
		suitables.add(false);
		result.addObject("suitables", suitables);
		result.addObject("message", message);
		result.addObject("requestUri", "report/employee/create.do");
		return result;

	}
}
