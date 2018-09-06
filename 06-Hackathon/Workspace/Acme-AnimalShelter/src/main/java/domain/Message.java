package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{
	
	// Constructors
	
	public Message(){
		super();
	}

	private Date moment;
	private String subject;
	private String body;
	private String priority;
	
	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@NotBlank
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@NotBlank
	@Pattern(regexp = "^((LOW)|(HIGH)|(NEUTRAL))$")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	// Relationships
	
	private Actor sender;
	private Collection<Actor> recipient;
	private Folder folder;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getSender() {
		return sender;
	}

	public void setSender(Actor sender) {
		this.sender = sender;
	}

	@Valid
	@OneToMany(mappedBy = "received")
	public Collection<Actor> getRecipient() {
		return recipient;
	}

	public void setRecipient(Collection<Actor> recipient) {
		this.recipient = recipient;
	}

	@Valid
	@ManyToOne
	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	
}
