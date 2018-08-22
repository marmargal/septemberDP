package controllers.innkeeper;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InnService;
import services.RegistrytService;
import controllers.AbstractController;
import domain.Inn;
import domain.Registry;
import forms.InnForm;

@Controller
@RequestMapping("/inn/innkeeper")
public class InnInnkeeperController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private InnService innService;

	@Autowired
	private RegistrytService registryService;

	// Constructors ---------------------------------------------------------

	public InnInnkeeperController() {
		super();
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(defaultValue = "0") final int innId) {
		ModelAndView result;
		Inn inn = null;

		if (innId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.innService.findOne(innId) == null) {
			result = new ModelAndView("redirect:../../");

		} else {
			inn = this.innService.findOne(innId);
			InnForm innForm = new InnForm();
			innForm.setAddress(inn.getAddress());
			innForm.setBadge(inn.getBadge());
			innForm.setCreditCard(inn.getCreditCard());
			innForm.setEmail(inn.getEmail());
			innForm.setId(inn.getId());
			innForm.setName(inn.getName());
			innForm.setPhoneNumber(inn.getPhoneNumber());
			innForm.setVersion(inn.getVersion());
			innForm.setWebSite(inn.getWebSite());
			innForm.setInnkeeper(inn.getInnkeeper());
			result = this.createEditModelAndView(innForm);
			result.addObject("innForm", innForm);
		}
		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid InnForm innForm, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(innForm, "inn.params.error");
		else
			try {
				Inn inn = this.innService.findOne(innForm.getId());
				Registry registry = this.registryService.create();
				registry.setDate(innForm.getDate());
				registry.setInn(inn);
				registryService.save(registry);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(innForm, "inn.commit.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final InnForm inn) {
		ModelAndView result;

		result = this.createEditModelAndView(inn, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final InnForm inn,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("inn/innkeeper/register");
		result.addObject("inn", inn);
		result.addObject("message", message);
		result.addObject("requestURI", "inn/innkeeper/register.do");

		return result;
	}

}
