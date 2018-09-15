package controllers.voluntary;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DonationService;
import services.EventService;
import controllers.AbstractController;
import domain.Donation;
import domain.Event;

@Controller
@RequestMapping("/donation/voluntary")
public class DonationVoluntaryController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private DonationService donationService;

	@Autowired
	private EventService eventService;

	// Constructors -----------------------------------------------------------

	public DonationVoluntaryController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(defaultValue = "0") int eventId) {
		ModelAndView res;
		Donation donation;
		Event event;
		
		if (eventId == 0) {
			res = new ModelAndView("redirect:../../");
		} else if (this.eventService.findOne(eventId) == null) {
			res = new ModelAndView("redirect:../../");
		} else {
			event = eventService.findOne(eventId);
			donation = this.donationService.create(event);
			res = this.createEditModelAndView(donation);
		}

		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Donation donation,
			final BindingResult binding) {
		ModelAndView res;
		Event event;
		Donation savedDonation;

		if (binding.hasErrors())
			res = this
					.createEditModelAndView(donation, "donation.params.error");
		else
			try {
				savedDonation = this.donationService.save(donation);
				event = donation.getEvent();
				event.getDonation().add(savedDonation);
//				eventService.save(event);
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

		result = new ModelAndView("donation/voluntary/edit");
		result.addObject("donation", donation);
		result.addObject("message", message);
		result.addObject("requestURI", "donation/voluntary/edit.do");

		return result;
	}
}
