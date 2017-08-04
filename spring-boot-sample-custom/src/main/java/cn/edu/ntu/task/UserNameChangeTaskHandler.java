package cn.edu.ntu.task;

import org.springframework.stereotype.Component;

import cn.edu.ntu.annotation.TaskHandler;
import cn.edu.ntu.entity.Task;
import cn.edu.ntu.entity.TaskResult;

@TaskHandler(taskType = "UserNameChange")
@Component
public class UserNameChangeTaskHandler extends AbstractTaskHandler {

	@Override
	TaskResult execute(Task task) {
		System.out.println("UserNameChange");
		return null;
	}

}
