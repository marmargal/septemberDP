package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Country extends DomainEntity {

	// Attributes

	private String name;
	private String isoCode;
	private String flag;
	private String link;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	@NotBlank
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@NotBlank
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	// Relationships
	
	private List<Law> law;

	@Valid
	@OneToMany
	public List<Law> getLaw() {
		return law;
	}

	public void setLaw(List<Law> law) {
		this.law = law;
	}
	
	

}
