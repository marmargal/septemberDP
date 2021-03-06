package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Country extends DomainEntity {

	// Constructors
	
	public Country(){
		super();
	}
	
	// Attributes

	private String name;
	private String isoCode;
	private String flag;
	private String link;

	@NotBlank
	@SafeHtml
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml
	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	@NotBlank
	@SafeHtml
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@NotBlank
	@SafeHtml
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	// Relationships
	
	private List<Law> law;

	@Valid
	@OneToMany(mappedBy="country")
	public List<Law> getLaw() {
		return law;
	}

	public void setLaw(List<Law> law) {
		this.law = law;
	}

}
