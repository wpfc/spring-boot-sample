package cn.edu.ntu.task;

import cn.edu.ntu.entity.Task;
import cn.edu.ntu.entity.TaskResult;

public abstract class AbstractTaskHandler {

	abstract TaskResult execute(Task task);
}
