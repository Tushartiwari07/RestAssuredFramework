package endPoints;

public interface IEndPointForProject {
	public String Add_Proj="/addProject";
	public String Get_All_Proj= "/projects";
	public String Get_Single_Proj="/project/{projectId}";
	public String Update_Proj="/project/{projectId}";
	public String Delete_Proj="/project/{projectId}";
	public String Get_Single_Proj_Via_FormParam="/project";
	public String GetProjectCount="/count-projects";
	
	
}
