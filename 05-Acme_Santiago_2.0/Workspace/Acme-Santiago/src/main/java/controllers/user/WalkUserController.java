package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import services.WalkService;
import controllers.AbstractController;
import domain.User;
import domain.Walk;

@Controller
@RequestMapping("/walk/user")
public class WalkUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private WalkService walkService;

	@Autowired
	private UserService userService;

	// Constructors ---------------------------------------------------------

	public WalkUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Walk> walks = new ArrayList<>();
		
		User user = userService.findByPrincipal();
		
		walks = this.walkService.findWalkByUser(user.getId());
		res = new ModelAndView("walk/user/list");
		res.addObject("requestURI", "walk/user/list.do");
		res.addObject("walks", walks);

		return res;
	}
}
