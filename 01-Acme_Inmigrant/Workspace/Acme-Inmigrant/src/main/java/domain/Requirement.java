package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Requirement extends DomainEntity {

	// Constructors
	
	public Requirement(){
		super();
	}
	
	// Attributes

	private String title;
	private String description;
	private boolean abrogated;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getAbrogated() {
		return abrogated;
	}

	public void setAbrogated(boolean abrogated) {
		this.abrogated = abrogated;
	}
	
}
