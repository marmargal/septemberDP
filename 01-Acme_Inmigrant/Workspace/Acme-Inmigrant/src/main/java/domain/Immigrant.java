package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "investigator_Id") })
public class Immigrant extends Actor {

	// Constructors

	public Immigrant() {
		super();
	}

	// Attributes


	// Relationships
	private Collection<Application> applications;
	private Collection<Answer> answers;
	private Investigator investigator;

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
	
	@Valid
	@OneToOne(optional = true)
	public Investigator getInvestigator(){
		return investigator;
	}
	
	public void setInvestigator(Investigator investigator){
		this.investigator = investigator;
	}

}
