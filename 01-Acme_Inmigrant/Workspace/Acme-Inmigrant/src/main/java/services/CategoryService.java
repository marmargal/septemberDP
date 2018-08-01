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
import domain.Visa;

@Service
@Transactional
public class CategoryService {

	// Managed repository

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AdministratorService administratorService;

	// Suporting services

	// Constructors

	public CategoryService() {
		super();
	}

	// Simple CRUD methods
	public Category create() {
		Category res = new Category();

		String name = "nombre";
		Boolean rootCategory = false;

		Category categoryParent = new Category();
		List<Category> categories = new ArrayList<Category>();
		List<Visa> visas = new ArrayList<Visa>();

		res.setName(name);
		res.setRootCategory(rootCategory);
		res.setCategoryParent(categoryParent);
		res.setCategories(categories);
		res.setVisas(visas);

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
		this.administratorService.checkAuthority();
		Assert.notNull(category);
		Assert.isTrue(!category.getName().equals("root"));
		Assert.isTrue(!category.getName().equals(
				category.getCategoryParent().getName()));
		Category saved = null;
		try {

			if (category.getId() == 0) {
				Assert.isTrue(!this.categoryRepository.existsThisCategoryName(
						category.getName(), category.getCategoryParent()
								.getId()));
				saved = this.categoryRepository.saveAndFlush(category);
				saved.getCategoryParent().getCategories().add(saved);
			} else {
				final String oldName = this.categoryRepository.findOne(
						category.getId()).getName();
				Category oldCategory = this.categoryRepository.findOne(category
						.getId());
				if (category.getName().equals(oldName)) {
					saved = this.categoryRepository.saveAndFlush(category);
					List<Category> oldCategories = new ArrayList<>(
							oldCategory.getCategories());
					oldCategories.remove(category);
					oldCategory.setCategories(oldCategories);
					this.categoryRepository.save(oldCategory);

					saved.getCategoryParent().getCategories().add(saved);

				} else {

					Assert.isTrue(!this.categoryRepository
							.existsThisCategoryName(category.getName(),
									category.getCategoryParent().getId()));
					saved = this.categoryRepository.saveAndFlush(category);
					oldCategory.getCategories().remove(category);
					saved.getCategoryParent().getCategories().add(saved);

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return saved;

	}

	public void delete(Category category) {
		this.administratorService.checkAuthority();

		Assert.notNull(category);
		Assert.isTrue(category.getId() != 0);
		Assert.isTrue(categoryRepository.exists(category.getId()));

		Category categoryParent = new Category();
		List<Category> categorysSonsOfParent = new ArrayList<Category>();
		List<Category> categorysSons = new ArrayList<Category>();
		// Actualizando Categorys hijas
		categorysSons = category.getCategories();
		if (!categorysSons.isEmpty()) {
			for (int i = 0; i < categorysSons.size(); i++) {
				categorysSons.get(i).setCategoryParent(
						categoryRepository.findCategories());
				this.save(categorysSons.get(i));
			}
		}

		// Actualizando categoryParent
		categoryParent = category.getCategoryParent();
		if (categoryParent != null) {
			categorysSonsOfParent = categoryParent.getCategories();
			categorysSonsOfParent.remove(category);
			categoryParent.setCategories(categorysSonsOfParent);
			this.save(categoryParent);
		}

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
