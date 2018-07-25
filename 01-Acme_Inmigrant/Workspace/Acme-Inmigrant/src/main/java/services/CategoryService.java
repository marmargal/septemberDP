package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	// Managed repository

	@Autowired
	private CategoryRepository categoryRepository;

	// Suporting services

	// Constructors

	public CategoryService() {
		super();
	}

	// Simple CRUD methods
	public Category create() {
		Category res = new Category();
		
		String name= "nombre";
		Boolean rootCategory=false;
		
		Category categoryParent= new Category();
		List<Category> categories= new ArrayList<Category>();
		
		res.setName(name);
		res.setRootCategory(rootCategory);
		res.setCategoryParent(categoryParent);
		res.setCategories(categories);

		return res;
	}

	public Collection<Category> findAll() {
		Collection<Category> res;
		res = categoryRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Category findOne(int categoryId) {
		Assert.isTrue(categoryId != 0);
		Category res;
		res = categoryRepository.findOne(categoryId);
		Assert.notNull(res);
		return res;
	}

	public Category save(Category category) {
		Category res;
		res = categoryRepository.save(category);
		return res;
	}

	public void delete(Category category) {
		Assert.notNull(category);
		Assert.isTrue(category.getId() != 0);
		Assert.isTrue(categoryRepository.exists(category.getId()));
		categoryRepository.delete(category);
	}
	
	// Other business methods
	
	public Collection<Category> getCategoryChildren() {
		Collection<Category> categories;
		Category c;
		c = this.categoryRepository.findCategories();
		Assert.notNull(c);
		categories = c.getCategories();
		Assert.notNull(categories);
		return categories;
	}

}
