package forms;

import domain.Center;

public class AssignEmployeeForm {

	private int		id;
	private int		employeeId;
	private Center	 center;
	
	public AssignEmployeeForm(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	

	
}
