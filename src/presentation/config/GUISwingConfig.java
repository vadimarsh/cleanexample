package presentation.config;

import data.repository.indatabase.InDBDiscipRepository;
import data.repository.indatabase.InDBTaskRepository;
import data.repository.infile.InFileDiscipRepository;
import data.repository.infile.InFileTaskRepository;
import data.storage.InFileIDGenerator;
import data.storage.InFileStorage;
import data.storage.SQLiteStorage;
import domain.port.DisciplineRepository;
import domain.port.TaskRepository;
import domain.usecase.discipline.RefreshDisciplineStatusUseCase;
import domain.usecase.discipline.CreateDisciplineUseCase;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import domain.usecase.task.*;

import javax.imageio.IIOException;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GUISwingConfig {
   private final SQLiteStorage sqLiteStorage = SQLiteStorage.getInstance();
    private final DisciplineRepository disciplineRepository = new InDBDiscipRepository(sqLiteStorage);
    private final TaskRepository taskRepository = new InDBTaskRepository(sqLiteStorage);

  /*  private final InFileIDGenerator disciplinesIDGenerator= new InFileIDGenerator("disciplines.key");
    private final InFileIDGenerator tasksIDGenerator= new InFileIDGenerator("tasks.key");
    private final InFileStorage inFileStorage = new InFileStorage(disciplinesIDGenerator,tasksIDGenerator);
    private final DisciplineRepository disciplineRepository = new InFileDiscipRepository(inFileStorage);
    private final TaskRepository taskRepository = new InFileTaskRepository(inFileStorage);

   */

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
    public CancelTaskUseCase cancelTaskUseCase() { return new CancelTaskUseCase(taskRepository);
    }
}
