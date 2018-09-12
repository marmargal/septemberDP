package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PlatformService;
import services.SubscriptionService;
import services.UserService;
import controllers.AbstractController;
import domain.Platform;
import domain.Subscription;
import domain.User;
import forms.SubscriptionForm;

@Controller
@RequestMapping("/subscription/user")
public class SubscriptionUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private PlatformService platformService;
	
	@Autowired
	private UserService userService;

	// Constructors ---------------------------------------------------------

	public SubscriptionUserController() {
		super();
	}
	
	// Listing
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		this.userService.checkAuthority();
		ModelAndView res;
		Collection<Subscription> subscriptions = new ArrayList<Subscription>();
		User user = this.userService.findByPrincipal();
		
		subscriptions = user.getSubscriptions();
	
		res = new ModelAndView("subscription/list");
		res.addObject("subscription", subscriptions);
		res.addObject("requestURI","subscription/user/list.do");

		return res;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		SubscriptionForm suscriptionForm = new SubscriptionForm();
		
		result = this.createEditModelAndView(suscriptionForm);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SubscriptionForm subscriptionForm, final BindingResult binding) {
		ModelAndView res;
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(subscriptionForm, "subscription.params.error");
		else
			try {
				Subscription subscription = this.subscriptionService.reconstruct(subscriptionForm, binding);
				this.subscriptionService.save(subscription);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(subscriptionForm, "subscription.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final SubscriptionForm subscriptionForm) {
		ModelAndView result;

		result = this.createEditModelAndView(subscriptionForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final SubscriptionForm subscriptionForm, final String message) {
		ModelAndView result;
		Collection<Platform> platformsNotSubscription = new ArrayList<Platform>();
		Collection<Platform> platformsWhitSubscription = new ArrayList<Platform>();
		User user = this.userService.findByPrincipal();

		platformsNotSubscription = this.platformService.findAll();
		platformsWhitSubscription = this.platformService.findPlatformByUser(user);
		platformsNotSubscription.removeAll(platformsWhitSubscription);
		
		
		result = new ModelAndView("subscription/create");
		result.addObject("subscriptionForm", subscriptionForm);
		result.addObject("platforms", platformsNotSubscription);
		result.addObject("message", message);
		result.addObject("requestUri", "subscription/user/create.do");
		return result;

	}
}