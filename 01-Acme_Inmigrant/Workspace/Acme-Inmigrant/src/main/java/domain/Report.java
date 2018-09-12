package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "writer_Id") })
public class Report extends DomainEntity {

	// Constructors
	
	public Report(){
		super();
	}
	
	// Attributes

	private String text;
	private String picture;

	@NotBlank
	@SafeHtml
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@URL
	@SafeHtml
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	// Relationships
	private Immigrant immigrant;
	private Investigator writer;
	
	@OneToOne
	@Valid
	@NotNull
	public Immigrant getImmigrant() {
		return immigrant;
	}

	public void setImmigrant(Immigrant immigrant) {
		this.immigrant = immigrant;
	}

	@Valid
	@ManyToOne
	public Investigator getWriter() {
		return writer;
	}

	public void setWriter(Investigator writer) {
		this.writer = writer;
	}
}
