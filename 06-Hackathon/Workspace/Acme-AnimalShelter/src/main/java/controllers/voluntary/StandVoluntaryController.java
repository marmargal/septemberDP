package controllers.voluntary;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.StandService;
import services.VoluntaryService;
import controllers.AbstractController;
import domain.Stand;
import domain.Voluntary;

@Controller
@RequestMapping("stand/voluntary")
public class StandVoluntaryController extends AbstractController {

	// Services ------------------------------------------

	@Autowired
	private StandService standService;
	
	@Autowired
	private VoluntaryService voluntaryService;

	// Constructors --------------------------------------

	public StandVoluntaryController() {
		super();
	}

	@RequestMapping(value = "/join")
	public ModelAndView edit(@RequestParam(defaultValue = "0") final int standId) {
		ModelAndView result;
		Stand stand = null;

		if (standId == 0) {
			result = new ModelAndView("redirect:../../");
		} else if (this.standService.findOne(standId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			stand = this.standService.findOne(standId);
			result = this.createEditModelAndView(stand);
		}

		return result;
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Stand stand, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(stand, "stand.params.error");
		else
			try {
				standService.joinVoluntary(stand);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(stand, "stand.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Stand stand) {
		ModelAndView result;

		result = this.createEditModelAndView(stand, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Stand stand,
			final String message) {
		ModelAndView result;
		Boolean isntThere = false;
		Voluntary voluntary = voluntaryService.findByPrincipal();
		
		if(!stand.getVoluntaries().contains(voluntary)){
			isntThere = true;
		}

		result = new ModelAndView("stand/voluntary/join");
		result.addObject("stand", stand);
		result.addObject("isntThere", isntThere);
		result.addObject("message", message);
		result.addObject("requestURI", "stand/voluntary/join.do");

		return result;
	}
}
