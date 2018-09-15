package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity{
	
	// Constructors
	
	public Folder(){
		super();
	}

	private String name;
	private Boolean predefined;
	
	@NotBlank
	@SafeHtml
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public Boolean getPredefined() {
		return predefined;
	}

	public void setPredefined(Boolean predefined) {
		this.predefined = predefined;
	}
	
	// Relationships

	private Actor actor;
	private Collection<Message> messages;
	
	@Valid
	@OneToMany(mappedBy = "folder")
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
}
