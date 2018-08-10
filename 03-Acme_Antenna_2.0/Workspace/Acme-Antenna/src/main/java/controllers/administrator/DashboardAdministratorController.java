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

		result.addObject("dataAntennasPerUser",
				administratorService.dataAntennasPerUser());
		result.addObject("dataQualityPerAntennas",
				administratorService.dataQualityPerAntennas());
		result.addObject("dataNumAntennasPerModel",
				administratorService.dataNumAntennasPerModel());

		result.addObject("Top3AntennaPerpopularity",
				administratorService.Top3AntennaPerpopularity());

		result.addObject("dataTutorialPerUser",
				administratorService.dataTutorialPerUser());

		result.addObject("dataNumCommentPerTutorial",
				administratorService.dataNumCommentPerTutorial());

		result.addObject("actorHasPublished",
				administratorService.actorHasPublished());

		result.addObject("dataNumRepliesPerComment",
				administratorService.dataNumRepliesPerComment());

		result.addObject("dataNumLengthOfComments",
				administratorService.dataNumLengthOfComments());

		result.addObject("dataNumPicturesPerTutorial",
				administratorService.dataNumPicturesPerTutorial());

		result.addObject("dataNumPicturesPerComment",
				administratorService.dataNumPicturesPerComment());

		result.addObject("dataNumRequestPerUser",
				administratorService.dataNumRequestPerUser());

		result.addObject("dataServicedRequestPerUser",
				administratorService.dataServicedRequestPerUser());

		result.addObject("dataNumRequestPerHandy",
				administratorService.dataNumRequestPerHandy());

		result.addObject("dataNumBannerPerAgent",
				administratorService.dataNumBannerPerAgent());

		result.addObject("topAgentNumberBanners",
				administratorService.topAgentNumberBanners());

		return result;
	}
}
