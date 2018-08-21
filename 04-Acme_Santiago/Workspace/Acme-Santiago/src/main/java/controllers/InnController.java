package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.InnService;
import domain.Inn;

@Controller
@RequestMapping("/inn")
public class InnController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private InnService innService;

	// Constructors ---------------------------------------------------------

	public InnController() {
		super();
	}
	//list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Inn> inn = new ArrayList<>();
		
		Calendar fecha = Calendar.getInstance();
		int year= fecha.get(Calendar.YEAR);
		int month= fecha.get(Calendar.MONTH);
		year= year%100;
		System.out.println(year);		
		inn= this.innService.findCcExpirationYear(year, month);
		
		res = new ModelAndView("inn/list");
		res.addObject("requestURI", "inn/list.do");
		res.addObject("inn", inn);

		return res;
	}

}
