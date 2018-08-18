package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import domain.User;

public class UserForm {
	
	@Valid
	private User user;
	private String confirmPassword;
	private Boolean terms;
	
	public UserForm() {
		super();
	}

	public UserForm(final User user) {
		this.user = user;
		this.confirmPassword = "";
		this.terms = false;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@NotNull
	public Boolean getTerms() {
		return terms;
	}
	public void setTerms(Boolean terms) {
		this.terms = terms;
	}

}