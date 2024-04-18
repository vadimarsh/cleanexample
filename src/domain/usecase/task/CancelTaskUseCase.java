package domain.usecase.task;

import domain.entity.Task;
import domain.exceptions.NoSuchTaskException;
import domain.port.TaskRepository;

public class CancelTaskUseCase {
    private final TaskRepository taskRepository;

    public CancelTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

    }

    public Task invoke(final Task task){
        if(!taskRepository.delete(task)){
          throw new NoSuchTaskException();
        }
        return task;
    }

}
