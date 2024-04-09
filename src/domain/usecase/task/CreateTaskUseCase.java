package domain.usecase.task;

import domain.entity.Task;
import domain.port.TaskRepository;

public class CreateTaskUseCase {
    private final TaskRepository taskRepository;

    public CreateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task invoke(final Task task){
        return taskRepository.create(task);
    }
}
