package domain.usecase.task;

import domain.entity.Task;
import domain.port.TaskRepository;

import java.util.List;

public class GetUnclosedTasks {
    public GetUnclosedTasks(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private final TaskRepository taskRepository;
    public List<Task> invoke(){

        return this.taskRepository.getUnclosedTasks();
    }
}
