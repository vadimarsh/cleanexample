package domain.usecase.task;

import domain.entity.Task;
import domain.port.TaskRepository;

public class SetDeadLineUseCase {
    private final TaskRepository taskRepository;

    public SetDeadLineUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task invoke(final Task task){
            return taskRepository.update(task);
    }
}
