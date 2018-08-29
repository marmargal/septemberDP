package controllers.innkeeper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.HikeService;
import services.InnService;
import services.InnkeeperService;
import services.RegistrytService;
import services.UserService;
import controllers.AbstractController;
import domain.Hike;
import domain.Inn;
import domain.Innkeeper;
import domain.Registry;
import domain.User;
import forms.InnForm;

@Controller
@RequestMapping("/inn/innkeeper")
public class InnInnkeeperController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private InnService innService;

	@Autowired
	private InnkeeperService innkeeperService;

	@Autowired
	private RegistrytService registryService;

	@Autowired
	private UserService userService;

	@Autowired
	private HikeService hikeService;

	// Constructors ---------------------------------------------------------

	public InnInnkeeperController() {
		super();
	}

	// list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Inn> inn = new ArrayList<>();

		Calendar fecha = Calendar.getInstance();
		int year = fecha.get(Calendar.YEAR);
		int month = fecha.get(Calendar.MONTH);
		year = year % 100;
		System.out.println(year);
		inn = this.innService.findCcExpirationYear(year, month);

		Innkeeper innkeeper = innkeeperService.findByPrincipal();

		Collection<Inn> innPrincipal = new ArrayList<>();
		innPrincipal = innkeeper.getInns();

		inn.retainAll(innPrincipal);

		boolean boton = true;

		res = new ModelAndView("inn/innkeeper/list");
		res.addObject("requestURI", "inn/innkeeper/list.do");
		res.addObject("inn", inn);
		res.addObject("boton", boton);

		return res;
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
		boolean a = false;
		if (binding.hasErrors())
			res = this.createEditModelAndView(innForm, "inn.params.error");
		else
			try {
				Inn inn = this.innService.findOne(innForm.getId());
				Registry registry = this.registryService.create();
				registry.setHike(innForm.getHike());
				registry.setUser(innForm.getUser());
				registry.setDate(innForm.getDate());
				registry.setInn(inn);
					a = this.registryService.findRegistry(registry.getDate(),
							registry.getInn(), registry.getUser(),registry.getHike()) == null;
					Assert.isTrue(a);
				registryService.save(registry);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				if (!a) {
					res = this.createEditModelAndView(innForm,
							"inn.commit.error.register");
				} else {
					res = this.createEditModelAndView(innForm,
							"inn.commit.error");
				}
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
		Collection<User> users = this.userService.findAll();
		Collection<Hike> hikes = this.hikeService.findByCity(inn.getAddress()
				.toArray()[2].toString());
		result.addObject("users", users);
		result.addObject("hikes", hikes);
		result.addObject("message", message);
		result.addObject("requestURI", "inn/innkeeper/register.do");

		return result;
	}

}
