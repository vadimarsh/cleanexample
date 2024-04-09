package domain.usecase.task;

import domain.entity.Task;
import domain.port.TaskRepository;

public class MarkDoneTaskUseCase {
    private final TaskRepository taskRepository;

    public MarkDoneTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task markDone(final Task task){
        task.setClosed(true);
        return taskRepository.update(task);
    }
}
