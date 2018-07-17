
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/terms")
public class TermsAndConditionsController extends AbstractController {

	// Services -------------------------------------------------------------

	// Constructors ---------------------------------------------------------

	public TermsAndConditionsController() {
		super();
	}

	// Listing --------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("terms/list");
		
		return result;
	}
}
