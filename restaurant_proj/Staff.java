package restaurant;

public class Staff {
	
	private String name;
	private String  gender;
	private String employeeID;
	private String jobTitle;
	
	public String getName() {
		return name;
	}
	
	public void setName(String names)
	{
		this.name=names;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gen)
	{
		this.gender=gen;
	}
	
	public String getEmployeeID() {
		return employeeID;
	}
	
	public void  setEmployeeID(String EmpID)
	{
		this.employeeID=EmpID;
	}
	
	public String getJobTitle()
	{
		return jobTitle;
	}
	
	public void setJobTitle(String jobs)
	{
		this.jobTitle=jobs;
	}


}