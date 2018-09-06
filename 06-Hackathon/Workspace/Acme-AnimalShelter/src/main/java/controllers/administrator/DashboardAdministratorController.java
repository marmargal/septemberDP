package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;

@Controller
@RequestMapping("/dashboard")
public class DashboardAdministratorController {

	@Autowired
	private AdministratorService administratorService;

	public DashboardAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		result = new ModelAndView("dashboard/list");

		result.addObject("dataApplicationPerClient",
				administratorService.dataApplicationPerClient());

		result.addObject("dataNoticePerVoluntary",
				administratorService.dataNoticePerVoluntary());

		result.addObject("dataApplicationPerEmployee",
				administratorService.dataApplicationPerEmployee());

		result.addObject("dataReportPorEmpleado",
				administratorService.dataReportPorEmpleado());
		result.addObject("dataMedicalReportPerVeterinary",
				administratorService.dataMedicalReportPerVeterinary());

		result.addObject("dataApplicationPerClientLastWeek",
				administratorService.dataApplicationPerClientLastWeek());

		result.addObject("dataMedicalReportPerVeterinaryLastWeek",
				administratorService.dataMedicalReportPerVeterinaryLastWeek());

		result.addObject("dataReportPerEmployeeLastWeek",
				administratorService.dataReportPerEmployeeLastWeek());
		// ---------------------------------------------------------------
		result.addObject("clientsWithMoreApplications",
				administratorService.clientsWithMoreApplications());

		result.addObject("veterinariesWithMoreMedicalReport",
				administratorService.veterinariesWithMoreMedicalReport());

		result.addObject("employeesWithMoreReports",
				administratorService.employeesWithMoreReports());

		result.addObject("employeesWithLessReports",
				administratorService.employeesWithLessReports());

		result.addObject("dataVeterinaryMore50MedicalReport",
				administratorService.dataVeterinaryMore50MedicalReport());

		return result;
	}
}
