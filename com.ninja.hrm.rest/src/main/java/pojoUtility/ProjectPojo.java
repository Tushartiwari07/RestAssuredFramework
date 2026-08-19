package pojoUtility;

public class ProjectPojo {
	
	String projectName;
	String createdBy;
	int teamSize;
	String status;
	private ProjectPojo() {}
	public ProjectPojo(String projectName, String createdBy, int teamSize, String status) {
		this.projectName = projectName;
		this.createdBy = createdBy;
		this.teamSize = teamSize;
		this.status = status;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public String getStatus() {
		return status;
	}
}
