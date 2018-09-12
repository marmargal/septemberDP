package forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Satellite;

public class AntennaForm {

	private int id;
	private String serialNumber;
	private String model;
	private String latitude;
	private String longitude;
	private String azimuth;
	private String elevation;
	private String quality;
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
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
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
	
	@Range(min=-90,max=90)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Range(min=-180,max=180)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Range(min=0,max=360)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAzimuth() {
		return azimuth;
	}
	public void setAzimuth(String azimuth) {
		this.azimuth = azimuth;
	}
	
	@Range(min=0,max=90)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getElevation() {
		return elevation;
	}
	public void setElevation(String elevation) {
		this.elevation = elevation;
	}
	
	@Range(min=0,max=100)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
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
