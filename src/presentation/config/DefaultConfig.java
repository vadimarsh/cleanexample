package presentation.config;

import adapter.repository.InMemoryDiscipRepository;
import adapter.repository.InMemoryTaskRepository;
import domain.port.DisciplineRepository;
import domain.port.TaskRepository;
import domain.usecase.discipline.CreateDisciplineUseCase;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import domain.usecase.task.CreateTaskUseCase;
import domain.usecase.task.GetAllTasksByDisciplineUseCase;
import domain.usecase.task.GetAllTasksUseCase;

public class DefaultConfig {
    private final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final DisciplineRepository disciplineRepository = new InMemoryDiscipRepository();

    public CreateTaskUseCase createTask(){
        return new CreateTaskUseCase(taskRepository);
    }

    public CreateDisciplineUseCase createDiscipline() {
        return new CreateDisciplineUseCase(disciplineRepository);
    }

    public GetAllDisciplinesUseCase getAllDiscliplines() {
    return new GetAllDisciplinesUseCase(disciplineRepository);
    }

    public GetAllTasksByDisciplineUseCase getAllTasksByDisclipline() {
        return new GetAllTasksByDisciplineUseCase(taskRepository);
    }
    public GetAllTasksUseCase getAllTasks() {
        return new GetAllTasksUseCase(taskRepository);
    }

}
