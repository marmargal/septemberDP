/*
 * AbstractController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;

@Controller
public class AbstractController {

	// Panic handler ----------------------------------------------------------

	@Autowired
	private ConfigurationService configurationService;

	@ExceptionHandler(Throwable.class)
	public ModelAndView panic(final Throwable oops) {
		ModelAndView result;

		result = new ModelAndView("misc/panic");
		result.addObject("name", ClassUtils.getShortName(oops.getClass()));
		result.addObject("exception", oops.getMessage());
		result.addObject("stackTrace", ExceptionUtils.getStackTrace(oops));

		return result;
	}

	@ModelAttribute(value = "welcomeEnglishMessage")
	protected String getWelcomeEnglishMessage() {
		String res;

		res = this.configurationService.findAll().iterator().next()
				.getEnglishWelcome();

		return res;
	}

	@ModelAttribute(value = "welcomeSpanishMessage")
	protected String getWelcomeSpanishMessage() {
		String res;

		res = this.configurationService.findAll().iterator().next()
				.getSpanishWelcome();

		return res;
	}

	@ModelAttribute(value = "banner")
	protected String banner() {
		String banner;

		banner = this.configurationService.findAll().iterator().next()
				.getBanner();

		return banner;
	}
}
