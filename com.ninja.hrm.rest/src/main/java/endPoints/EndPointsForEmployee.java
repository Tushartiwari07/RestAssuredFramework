package endPoints;

public interface EndPointsForEmployee {

	
	
	public String getAllEmployee ="/employees";
	
	public String getCountOfEmployees= "/count-employees";
	
	public String getAllEmployeesAssociatedToProject ="/employee";
	
	public String deleteEmployee ="/employee/{empId}";
	
	public String updateEmployee ="/employee/{employeeId}";
	
	public String updateEmployeeUsingPatchApi ="/employee/{id}";
	
	public String getEmployeeByUserName ="/employee/{userName}";
	
	public String ApiTogetEmployeeExperiences= "/employee/getExperiences";
	
	public String getAllEmployees ="/all-employees";
	
	public String createEmployee ="/employees";
	
	public String getEmployeeByEmpID ="/employees/{empId}";
	
	public String updateProject= "/employees/{empId}";
	
	public String resetPassword ="/employees/resetPassword";
	
	public String addUser ="/signup";
	
	public String validateUserName ="/signup/{username}";
	
	
	
	
	
	
	
	
}