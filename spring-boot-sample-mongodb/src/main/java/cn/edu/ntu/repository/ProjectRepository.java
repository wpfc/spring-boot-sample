package cn.edu.ntu.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.edu.ntu.entity.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {

}
