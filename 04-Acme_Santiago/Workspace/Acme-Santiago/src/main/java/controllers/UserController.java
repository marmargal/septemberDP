/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public UserController() {
		super();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView searchList(@RequestParam(defaultValue = "0") int userId) {
		ModelAndView res;
		User user;
		try {

			user = this.userService.findOne(userId);
			res = new ModelAndView("user/display");
			res.addObject("user", user);
			res.addObject("requestURI", "user/display.do");
		} catch (Exception e) {
			res = new ModelAndView("redirect:/user/list.do");
		}

		return res;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<User> users = new ArrayList<>();
		users = this.userService.findAll();
		res = new ModelAndView("user/list");
		res.addObject("users", users);
		res.addObject("requestURI", "user/list.do");

		return res;
	}

}
