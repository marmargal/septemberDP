package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Notice extends DomainEntity{
	
	// Constructors
	
	public Notice(){
		super();
	}

	private String description;
	private String type;
	private GpsCoordinate gpsCoordinates;
	private Integer level;
	private Date date;
	private Boolean descarted;
	
	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@NotNull
	public GpsCoordinate getGpsCoordinates() {
		return gpsCoordinates;
	}

	public void setGpsCoordinates(GpsCoordinate gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}

	@NotNull
	@Range(min=1,max=3)
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@NotNull
	public Boolean getDescarted() {
		return descarted;
	}

	public void setDescarted(Boolean descarted) {
		this.descarted = descarted;
	}
	
	// Relationships

	private Voluntary voluntary;
	
	@Valid
	@ManyToOne(optional=false)
	public Voluntary getVoluntary() {
		return voluntary;
	}

	public void setVoluntary(Voluntary voluntary) {
		this.voluntary = voluntary;
	}

	
}
