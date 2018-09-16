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
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.Compostela;
import domain.Route;
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
		User currentUser;
		Integer currentUserId;

		if (userId == 0) {
			res = new ModelAndView("redirect:../");

		} else if (this.userService.findOne(userId) == null) {
			res = new ModelAndView("redirect:../");
		} else {

			user = this.userService.findOne(userId);
			Set<Route> routes = new HashSet<>();
			for (Compostela com : user.getCompostelas()) {
				if (com.isDecision() && com.isfinallyDecision()) {
					routes.add((com.getWalk().getRoute()));
				}
			}
			res = new ModelAndView("user/display");
			res.addObject("user", user);
			res.addObject("routes", routes);
			res.addObject("followTable", user);
			try {

				currentUser = this.userService.findByPrincipal();
				currentUserId = currentUser.getId();
				res.addObject("currentUserId", currentUserId);
			} catch (Exception e) {
				res.addObject("currentUserId", userId);
			}
			res.addObject("requestURI", "user/display.do");
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

	// Followers and Following

	@RequestMapping(value = "/listFollowers", method = RequestMethod.GET)
	public ModelAndView listFollowers(@RequestParam int userId) {
		ModelAndView result;
		User user;
		Collection<User> followers;

		user = this.userService.findOne(userId);
		followers = user.getFollowers();

		result = new ModelAndView("user/listFollowers");
		result.addObject("users", followers);
		result.addObject("requestURI", "user/listFollowers.do");

		return result;
	}

	@RequestMapping(value = "/listFollowing", method = RequestMethod.GET)
	public ModelAndView listFollowing(@RequestParam int userId) {
		ModelAndView result;
		User user;
		Collection<User> following;

		user = this.userService.findOne(userId);
		following = user.getFollowing();

		result = new ModelAndView("user/listFollowing");
		result.addObject("users", following);
		result.addObject("requestURI", "user/listFollowing.do");

		return result;
	}

	// Follow

	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView follow(@RequestParam int userId) {
		ModelAndView result;
		User user;

		user = userService.findOne(userId);
		userService.follow(userId);
		result = this.createEditModelAndView(user);

		return result;
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public ModelAndView unfollow(@RequestParam int userId) {
		ModelAndView result;
		User user;

		user = userService.findOne(userId);
		userService.unfollow(userId);
		result = this.createEditModelAndViewUnfollow(user);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final User user) {
		ModelAndView result;

		result = this.createEditModelAndView(user, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final User user,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("user/follow");
		result.addObject("message", message);
		result.addObject("requestURI", "user/follow.do");
		return result;

	}

	protected ModelAndView createEditModelAndViewUnfollow(final User user) {
		ModelAndView result;

		result = this.createEditModelAndView(user, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewUnfollow(final User user,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("user/unfollow");
		result.addObject("message", message);
		result.addObject("requestURI", "user/unfollow.do");
		return result;

	}

}
