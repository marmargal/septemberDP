package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Company extends DomainEntity{
	
	// Constructors
	
	public Company(){
		super();
	}

	private String name;
	private String description;
	private String articles;
	private String logo;
	

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getArticles() {
		return articles;
	}

	public void setArticles(String articles) {
		this.articles = articles;
	}

	@URL
	@NotBlank
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	// Relationships

	private Collection<Stand> stands;
	
	@Valid
	@OneToMany(mappedBy = "company")
	public Collection<Stand> getStands() {
		return stands;
	}

	public void setStands(Collection<Stand> stands) {
		this.stands = stands;
	}
	
	
}
