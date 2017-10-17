package cn.edu.ntu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import cn.edu.ntu.entity.Project;
import cn.edu.ntu.repository.ProjectRepository;
import cn.edu.ntu.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public List<Project> getProjectList() {
		
//		return mongoTemplate.findAll(Project.class, "project");
		
		return projectRepository.findAll();
	}

}
