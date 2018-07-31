package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Antenna extends DomainEntity {

	// Constructors
	public Antenna(){
		super();
	}
	
	// Attributes
	private Integer serialNumber;
	private String model;
	private GpsCoordinate coordinates;
	private Double azimuth;
	private Double elevation;
	private Double quality;
	
	//TODO: pattern??
	public Integer getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@NotBlank
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	@NotNull
	public GpsCoordinate getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(GpsCoordinate coordinates) {
		this.coordinates = coordinates;
	}
	
	@Range(min=0,max=360)
	public Double getAzimuth() {
		return azimuth;
	}
	
	public void setAzimuth(Double azimuth) {
		this.azimuth = azimuth;
	}
	
	@Range(min=0,max=90)
	public Double getElevation() {
		return elevation;
	}
	
	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}
	
	@Range(min=0,max=100)
	public Double getQuality() {
		return quality;
	}
	
	public void setQuality(Double quality) {
		this.quality = quality;
	}
	
	// Relationships
	private User user;
	private Satellite satellite;
	
	@Valid
	@ManyToOne(optional=true)
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Valid
	@ManyToOne(optional=false)
	public Satellite getSatellite() {
		return satellite;
	}
	public void setSatellite(Satellite satellite) {
		this.satellite = satellite;
	}
	
	
}
