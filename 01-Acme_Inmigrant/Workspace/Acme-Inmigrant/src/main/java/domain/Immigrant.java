package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Immigrant extends Actor {

	// Constructors

	public Immigrant() {
		super();
	}

	// Attributes

	private Boolean investigated;

	public Boolean getInvestigated() {
		return investigated;
	}

	public void setInvestigated(Boolean investigated) {
		this.investigated = investigated;
	}

	// Relationships
	private Collection<Application> applications;
	private Collection<Answer> answers;

	@Valid
	@OneToMany(mappedBy = "immigrant")
	public Collection<Application> getApplications() {
		return applications;
	}

	public void setApplications(Collection<Application> applications) {
		this.applications = applications;
	}

	@Valid
	@OneToMany(mappedBy = "immigrant")
	public Collection<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Collection<Answer> answers) {
		this.answers = answers;
	}

}
