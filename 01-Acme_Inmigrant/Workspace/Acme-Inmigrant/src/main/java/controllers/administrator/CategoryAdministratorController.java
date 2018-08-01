package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;

import controllers.AbstractController;
import domain.Category;
import domain.Law;
import forms.CountryForm;
import forms.LawForm;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	@Autowired
	private CategoryService categoryService;

	public CategoryAdministratorController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Category category = categoryService.create();

		res = this.createEditModelAndView(category);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId) {
		ModelAndView res;
		Category category = categoryService.findOne(categoryId);

		res = this.createEditModelAndView(category);
		res.addObject("category", category);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Category category,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(category, "law.params.error");

		} else
			try {

				this.categoryService.save(category);

				res = new ModelAndView("redirect:/category/list.do");
			} catch (final Throwable oops) {

				res = this.createEditModelAndView(category, "law.commit.error");
			}

		return res;
	}

	private ModelAndView createEditModelAndView(Category category) {
		ModelAndView res;

		res = this.createEditModelAndView(category, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Category category,
			final String message) {
		ModelAndView res;
		Collection<Category> parents = categoryService.findAll();
		if (parents.contains(category)) {
			parents.remove(category);

		}
		res = new ModelAndView("category/edit");
		res.addObject("parents", parents);
		res.addObject("category", category);
		res.addObject("message", message);

		return res;
	}
}
