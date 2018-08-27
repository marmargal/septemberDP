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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		result = new ModelAndView("dashboard/list");

		result.addObject("dataRoutesPerUser",
				administratorService.dataRoutesPerUser());

		result.addObject("dataHikesPerRoute",
				administratorService.dataHikesPerRoute());

		result.addObject("dataLengthOfRoutes",
				administratorService.dataLengthOfRoutes());

		result.addObject("dataLengthOfHikes",
				administratorService.dataLengthOfHikes());
		result.addObject("dataNumChirpsPerUser",
				administratorService.dataNumChirpsPerUser());

		result.addObject("dataUserMore75Chirps",
				administratorService.dataUserMore75Chirps());

		result.addObject("dataUserLess25Chirps",
				administratorService.dataUserLess25Chirps());

		result.addObject("dataCommentPerRoute",
				administratorService.dataCommentPerRoute());

		result.addObject("dataInnsPerInnkeeper",
				administratorService.dataInnsPerInnkeeper());

		result.addObject("dataNumUserPerDayInns",
				administratorService.dataNumUserPerDayInns());

		result.addObject("dataOutlierOfRoutes",
				administratorService.dataOutlierOfRoutes());

		return result;
	}
}
