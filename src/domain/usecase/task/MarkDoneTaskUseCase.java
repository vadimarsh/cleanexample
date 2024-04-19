package domain.usecase.task;

import domain.entity.Task;
import domain.port.TaskRepository;

public class MarkDoneTaskUseCase {
    private final TaskRepository taskRepository;

    public MarkDoneTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task invoke(final Task task){
        task.changeState();
        return taskRepository.update(task);
    }
}
