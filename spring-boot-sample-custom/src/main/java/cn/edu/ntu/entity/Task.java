package cn.edu.ntu.entity;

/**
 * ÈÎÎñ
 * @author weipeng
 */
public class Task {

	private Long taskId;
	
	private String taskType;
	
	private String taskContent;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	
	
}
