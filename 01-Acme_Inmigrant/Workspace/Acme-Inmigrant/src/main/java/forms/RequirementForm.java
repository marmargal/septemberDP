package forms;

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
	private boolean abrogated;
	private int lawId;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
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
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public boolean getAbrogated() {
		return abrogated;
	}
	public void setAbrogated(boolean abrogated) {
		this.abrogated = abrogated;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public int getLawId() {
		return lawId;
	}
	public void setLawId(int lawId) {
		this.lawId = lawId;
	}
	
	
}
