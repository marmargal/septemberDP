package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;

@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController {

	@Autowired
	private AdministratorService administratorService;

	public DashboardAdministratorController() {
		super();
	}

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView dashboard() {
//		ModelAndView result;
//		result = new ModelAndView("dashboard/list");
//		result.addObject("dataApplicationPerImmigrant",
//				administratorService.dataApplicationPerImmigrant());
//
//		result.addObject("dataApplicationsPerOfficer",
//				administratorService.dataApplicationsPerOfficer());
//
//		result.addObject("dataPricePerVisa",
//				administratorService.dataPricePerVisa());
//
//		result.addObject("dataImmigrantsInvestigated",
//				administratorService.dataImmigrantsInvestigated());
//
//		result.addObject("dataTimeToMakeDecision",
//				administratorService.dataTimeToMakeDecision());
//
//		result.addObject("dataVisasPerCategory",
//				administratorService.dataVisasPerCategory());
//
//		result.addObject("dataLawsPerCountry",
//				administratorService.dataLawsPerCountry());
//
//		result.addObject("dataRequirementsPerVisa",
//				administratorService.dataRequirementsPerVisa());
//
//		return result;
//	}
}