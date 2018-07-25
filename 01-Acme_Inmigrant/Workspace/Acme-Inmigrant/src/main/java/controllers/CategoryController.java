package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import domain.Category;

@Controller
@RequestMapping("/category")
public class CategoryController {
	// Services -------------------------------------------------------------

		@Autowired
		private CategoryService categoryService;
		
		// Constructors ---------------------------------------------------------

		public CategoryController() {
			super();
		}

		// Listing --------------------------------------------------------------

		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Category> categories;
			Category category;
			Category categoryParent;
			
			categories = this.categoryService.getCategoryChildren();
			category = (Category) categories.toArray()[0];
			categoryParent = category.getCategoryParent();
			
			result = new ModelAndView("category/list");
			result.addObject("categories", categories);
			result.addObject("categoryParent", categoryParent);

			return result;
		}
		
		@RequestMapping(value = "/list", method = RequestMethod.GET, params = "categoryId")
		public ModelAndView list(@RequestParam final int categoryId) {
			ModelAndView result;
			Category categoryParent;
			Collection<Category> categories;

			categoryParent = this.categoryService.findOne(categoryId);
			Assert.notNull(categoryParent);
			categories = categoryParent.getCategories();

			result = new ModelAndView("category/list");
			result.addObject("categories", categories);
			result.addObject("categoryParent", categoryParent);

			return result;
		}
}
