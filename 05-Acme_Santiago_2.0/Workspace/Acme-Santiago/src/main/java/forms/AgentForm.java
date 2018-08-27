package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import domain.Agent;

public class AgentForm {
	
	@Valid
	private Agent agent;
	private String confirmPassword;
	private Boolean terms;
	
	public AgentForm() {
		super();
	}

	public AgentForm(final Agent agent) {
		this.agent = agent;
		this.confirmPassword = "";
		this.terms = false;
	}
	
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
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