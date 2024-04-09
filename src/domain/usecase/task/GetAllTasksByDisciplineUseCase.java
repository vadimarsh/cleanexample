package domain.usecase.task;

import domain.entity.Discipline;
import domain.entity.Task;
import domain.port.TaskRepository;

import java.util.List;

public class GetAllTasksByDisciplineUseCase {
    private TaskRepository taskRepository;

    public GetAllTasksByDisciplineUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> invoke(Discipline discipline){
        return taskRepository.findByDiscipline(discipline);
    }
}
