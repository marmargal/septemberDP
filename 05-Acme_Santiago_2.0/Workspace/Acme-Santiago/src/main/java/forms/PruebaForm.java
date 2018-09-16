package forms;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import domain.Route;

public class PruebaForm {

	private int id;
	private String title;
	private String description;
	private Route route;
	private int gauge;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotBlank
	@Size(min = 1, max = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@Size(min = 1, max = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Range(min = 1, max = 3)
	public int getGauge() {
		return gauge;
	}

	public void setGauge(int gauge) {
		this.gauge = gauge;
	}
	
	@Valid
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}


}
