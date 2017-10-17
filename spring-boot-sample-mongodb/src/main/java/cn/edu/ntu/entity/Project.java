package cn.edu.ntu.entity;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="project")
public class Project {

	@Id
	private String projectId;
	
	@Field("projectname")
	private String projectName;
	
	private String projectAddr;
	
	private Map<String, Object> custom_attr;
	
	private List<Object> option_list;
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectAddr() {
		return projectAddr;
	}

	public void setProjectAddr(String projectAddr) {
		this.projectAddr = projectAddr;
	}

	public Map<String, Object> getCustom_attr() {
		return custom_attr;
	}

	public void setCustom_attr(Map<String, Object> custom_attr) {
		this.custom_attr = custom_attr;
	}

	public List<Object> getOption_list() {
		return option_list;
	}

	public void setOption_list(List<Object> option_list) {
		this.option_list = option_list;
	}
	
}
