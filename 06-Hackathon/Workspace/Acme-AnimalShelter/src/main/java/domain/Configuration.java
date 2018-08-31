package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Constructors

	public Configuration() {
		super();
	}

	// Attributes

	private String banner;
	private String englishWelcome;
	private String spanishWelcome;
	private String countryCode;

	@URL
	@NotBlank
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	@NotBlank
	public String getEnglishWelcome() {
		return englishWelcome;
	}

	public void setEnglishWelcome(String englishWelcome) {
		this.englishWelcome = englishWelcome;
	}

	@NotBlank
	public String getSpanishWelcome() {
		return spanishWelcome;
	}

	public void setSpanishWelcome(String spanishWelcome) {
		this.spanishWelcome = spanishWelcome;
	}

	@NotBlank
	// TODO: hacer pattern.
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
