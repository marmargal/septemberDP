package forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class RequirementForm {
	
	public RequirementForm(){
		super();
	}

	private int id;
	private String title;
	private String description;
	private Boolean abrogated;
	private Integer lawId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotNull
	public Boolean getAbrogated() {
		return abrogated;
	}
	public void setAbrogated(Boolean abrogated) {
		this.abrogated = abrogated;
	}
	
	@NotNull
	public Integer getLawId() {
		return lawId;
	}
	public void setLawId(Integer lawId) {
		this.lawId = lawId;
	}
	
	
	
}
