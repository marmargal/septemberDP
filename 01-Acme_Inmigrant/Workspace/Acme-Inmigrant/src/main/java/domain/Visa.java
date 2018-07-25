package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	private Integer price;
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

	@NotNull
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
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
	@NotNull
	@ManyToOne(optional=false)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
