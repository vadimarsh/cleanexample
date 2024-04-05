package usecase;

import domain.entity.Task;
import usecase.port.TaskRepository;

public class CreateTask {
    private TaskRepository taskRepository;

    public CreateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(final Task task){
        return taskRepository.create(task);
    }
}
