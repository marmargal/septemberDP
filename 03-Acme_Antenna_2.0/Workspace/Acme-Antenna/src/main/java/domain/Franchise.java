package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Franchise extends DomainEntity {
	
	// Attributes --------------------------------------------------
	
	private String businessName;
	private String banner;
	private String welcomeEnglishMessage;
	private String welcomeSpanishMessage;
	
	@NotBlank
	public String getBusinessName() {
		return businessName;
	}
	
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	@URL
	@NotBlank
	public String getBanner() {
		return banner;
	}
	
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	@NotBlank
	public String getWelcomeEnglishMessage() {
		return welcomeEnglishMessage;
	}
	
	public void setWelcomeEnglishMessage(String welcomeEnglishMessage) {
		this.welcomeEnglishMessage = welcomeEnglishMessage;
	}
	
	@NotBlank
	public String getWelcomeSpanishMessage() {
		return welcomeSpanishMessage;
	}
	
	public void setWelcomeSpanishMessage(String welcomeSpanishMessage) {
		this.welcomeSpanishMessage = welcomeSpanishMessage;
	}
	
	// Relationships -----------------------------------------------

}