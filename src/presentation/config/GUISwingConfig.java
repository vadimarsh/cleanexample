package presentation.config;

import data.repository.inmemory.InMemoryDiscipRepository;
import data.repository.inmemory.InMemoryTaskRepository;
import domain.port.DisciplineRepository;
import domain.port.TaskRepository;
import domain.usecase.discipline.RefreshDisciplineStatusUseCase;
import domain.usecase.discipline.CreateDisciplineUseCase;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import domain.usecase.task.*;

public class GUISwingConfig {
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

    public MarkDoneTaskUseCase markDoneTask() { return new MarkDoneTaskUseCase(taskRepository);}

    public RefreshDisciplineStatusUseCase refreshDisciplineStatus() { return new RefreshDisciplineStatusUseCase(disciplineRepository,taskRepository);
    }

    public GetUnclosedTasks getUnclosedTasks() { return new GetUnclosedTasks(taskRepository);
    }

    public SetDeadLineUseCase setDeadLine() { return new SetDeadLineUseCase(taskRepository);
    }
}
