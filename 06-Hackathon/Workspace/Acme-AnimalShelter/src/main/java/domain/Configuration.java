package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
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
	@SafeHtml
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	@NotBlank
	@SafeHtml
	public String getEnglishWelcome() {
		return englishWelcome;
	}

	public void setEnglishWelcome(String englishWelcome) {
		this.englishWelcome = englishWelcome;
	}

	@NotBlank
	@SafeHtml
	public String getSpanishWelcome() {
		return spanishWelcome;
	}

	public void setSpanishWelcome(String spanishWelcome) {
		this.spanishWelcome = spanishWelcome;
	}

	@NotBlank
	@SafeHtml
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
