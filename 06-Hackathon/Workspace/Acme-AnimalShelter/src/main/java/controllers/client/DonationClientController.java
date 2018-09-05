package controllers.client;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.DonationService;
import domain.Donation;

@Controller
@RequestMapping("/donation/client")
public class DonationClientController {
	// Services -------------------------------------------------------------

	@Autowired
	private DonationService donationService;

	// Constructors -----------------------------------------------------------

	public DonationClientController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Donation donation;

		donation = this.donationService.create();
		res = this.createEditModelAndView(donation);

		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Donation donation,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this
					.createEditModelAndView(donation, "donation.params.error");
		else
			try {
				this.donationService.save(donation);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(donation,
						"donation.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Donation donation) {
		ModelAndView result;

		result = this.createEditModelAndView(donation, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Donation donation,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("donation/client/edit");
		result.addObject("donation", donation);
		result.addObject("message", message);
		result.addObject("requestURI", "donation/client/edit.do");

		return result;
	}
}
