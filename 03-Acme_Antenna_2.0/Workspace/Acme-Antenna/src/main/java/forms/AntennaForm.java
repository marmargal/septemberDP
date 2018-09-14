package forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Satellite;

public class AntennaForm {

	private int id;
	private Integer serialNumber;
	private String model;
	private Double latitude;
	private Double longitude;
	private Double azimuth;
	private Double elevation;
	private Double quality;
	private Satellite satellite;
	
	public AntennaForm(){
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotNull
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@NotNull
	@Range(min=-90,max=90)
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	@NotNull
	@Range(min=-180,max=180)
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@NotNull
	@Range(min=0,max=360)
	public Double getAzimuth() {
		return azimuth;
	}
	public void setAzimuth(Double azimuth) {
		this.azimuth = azimuth;
	}
	
	@NotNull
	@Range(min=0,max=90)
	public Double getElevation() {
		return elevation;
	}
	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}
	
	@NotNull
	@Range(min=0,max=100)
	public Double getQuality() {
		return quality;
	}
	public void setQuality(Double quality) {
		this.quality = quality;
	}

	@NotNull
	public Satellite getSatellite() {
		return satellite;
	}

	public void setSatellite(Satellite satellite) {
		this.satellite = satellite;
	}
	
}
