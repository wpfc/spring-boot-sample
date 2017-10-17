package cn.edu.ntu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.ntu.entity.Project;
import cn.edu.ntu.service.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/getProjectList")
	public List<Project> getProjectList(){
		return projectService.getProjectList();
	}
	
}
