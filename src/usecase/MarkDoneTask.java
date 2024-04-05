package usecase;

import domain.entity.Task;
import usecase.port.TaskRepository;

public class MarkDoneTask {
    private TaskRepository taskRepository;

    public MarkDoneTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task markDone(final Task task){
        task.setClosed(true);
        return taskRepository.update(task);
    }
}
