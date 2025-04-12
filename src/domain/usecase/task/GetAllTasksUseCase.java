package domain.usecase.task;

import domain.entity.Task;
import domain.port.TaskRepository;

import java.util.List;

public class GetAllTasksUseCase {
    public GetAllTasksUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private final TaskRepository taskRepository;
    public List<Task> invoke(){
        return taskRepository.getAll();
    }
}
