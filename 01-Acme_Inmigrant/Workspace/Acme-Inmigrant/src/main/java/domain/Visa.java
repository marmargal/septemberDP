package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Visa extends DomainEntity {

	// Constructors

	public Visa() {
		super();
	}

	// Attributes

	private String classes;
	private String description;
	private String price;
	private Boolean invalidate;

	@NotBlank
	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Boolean getInvalidate() {
		return invalidate;
	}

	public void setInvalidate(Boolean invalidate) {
		this.invalidate = invalidate;
	}

	// Relationships

	private Country country;
	private Category category;

	@Valid
	@OneToOne(optional = false)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Valid
	@OneToOne(optional = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
