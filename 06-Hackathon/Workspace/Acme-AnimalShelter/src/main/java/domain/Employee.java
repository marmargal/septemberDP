package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "center_id") })
public class Employee extends Actor{
	
	// Constructors
	
	public Employee(){
		super();
	}
	
	private boolean ban;

	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
	}
	
	// Relationships

	private Stand stand;
	private Collection<Report> reports;
	private Center center;
	
	@Valid
	@OneToOne(optional=true)
	public Stand getStand() {
		return stand;
	}
	public void setStand(Stand stand) {
		this.stand = stand;
	}

	@Valid
	@OneToMany(mappedBy = "employee")
	public Collection<Report> getReports() {
		return reports;
	}

	public void setReports(Collection<Report> reports) {
		this.reports = reports;
	}

	@Valid
	@ManyToOne(optional=false)
	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}
	
}
