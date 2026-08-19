package pojoUtility;

public class PartialUpdateEmployeePojo {
	
	String designation;
	String empName;
	double experience;
	String mobileNo;
	String role;
	public PartialUpdateEmployeePojo(String designation, String empName, double experience) {
		this.designation = designation;
		this.empName = empName;
		this.experience = experience;
	
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
	
}