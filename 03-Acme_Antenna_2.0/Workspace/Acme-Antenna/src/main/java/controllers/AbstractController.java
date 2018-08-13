package controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import services.FranchiseService;
import domain.Franchise;

@Controller
public class AbstractController {
	
	@Autowired
	private FranchiseService franchiseService;

	// Panic handler ----------------------------------------------------------

	@ExceptionHandler(Throwable.class)
	public ModelAndView panic(final Throwable oops) {
		ModelAndView result;

		result = new ModelAndView("misc/panic");
		result.addObject("name", ClassUtils.getShortName(oops.getClass()));
		result.addObject("exception", oops.getMessage());
		result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));

		return result;
	}
	
	@ModelAttribute(value = "bannerShowImage")
	protected String banner(){
		String banner;
		Franchise franchise;
		
		Integer id = franchiseService.resId();
		franchise = franchiseService.findOne(id);
		banner = franchise.getBanner();
		
		return banner;
	}
	
	@ModelAttribute(value = "businessName")
	protected String getBusinessName(){
		String res;

		res = this.franchiseService.findAll().iterator().next().getBusinessName();
		
		return res;
	}
	
	@ModelAttribute(value = "welcomeEnglishMessage")
	protected String getWelcomeEnglishMessage(){
		String res;

		res = this.franchiseService.findAll().iterator().next().getWelcomeEnglishMessage();
		
		return res;
	}
	
	@ModelAttribute(value = "welcomeSpanishMessage")
	protected String getWelcomeSpanishMessage(){
		String res;

		res = this.franchiseService.findAll().iterator().next().getWelcomeSpanishMessage();
		
		return res;
	}

}
