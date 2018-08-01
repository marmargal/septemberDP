package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity{
	
	// Constructors
	public Comment(){
		super();
	}
	
	// Attributes
	private Date moment;
	private String title;
	private String text;
	//TODO: pictures como String y se separan por split o como collection?
	private String pictures;
	
	@Past
	public Date getMoment() {
		return moment;
	}
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@URL
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}  
	
	

	// Relationships
	private Tutorial tutorial;
	private User user;
	private Comment commentParent;
	private Collection<Comment> replies;
	
	@Valid
	@ManyToOne
	public Tutorial getTutorial() {
		return tutorial;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}


	@Valid
	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Valid
	@ManyToOne(optional=true)
	public Comment getCommentParent() {
		return commentParent;
	}

	public void setCommentParent(Comment commentParent) {
		this.commentParent = commentParent;
	}

	@Valid
	@OneToMany(mappedBy="commentParent")
	public Collection<Comment> getReplies() {
		return replies;
	}

	public void setReplies(Collection<Comment> replies) {
		this.replies = replies;
	}
	
	
	

}
