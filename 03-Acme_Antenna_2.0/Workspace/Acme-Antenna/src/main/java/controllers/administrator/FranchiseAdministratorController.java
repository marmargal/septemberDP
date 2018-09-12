package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FranchiseService;
import controllers.AbstractController;
import domain.Franchise;

@Controller
@RequestMapping("/administrator")
public class FranchiseAdministratorController extends AbstractController {
	
	// Services -------------------------------------------------------------

	@Autowired
	private FranchiseService franchiseService;
	
	// Constructors ---------------------------------------------------------

	public FranchiseAdministratorController() {
		super();
	}
	
	// Editing ---------------------------------------------------------------
	
	@RequestMapping(value = "/franchise", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Franchise franchise;
		
		Integer confId = franchiseService.resId();
		
		franchise = franchiseService.findOne(confId);
		Assert.notNull(franchise);
		result = this.createEditModelAndView(franchise);

		return result;
	}
	
	@RequestMapping(value = "/franchise", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Franchise franchise,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(franchise, "administrator.params.error");
		else
			try {
				this.franchiseService.save(franchise);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(franchise, "administrator.commit.error");
			}

		return res;
	}
	
	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Franchise franchise) {
		ModelAndView result;

		result = this.createEditModelAndView(franchise, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(final Franchise franchise,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("administrator/franchise");
		result.addObject("franchise", franchise);
		result.addObject("message", message);
		result.addObject("requestURI","administrator/franchise.do");
		return result;
	}
}