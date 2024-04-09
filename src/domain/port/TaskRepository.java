package domain.port;

import domain.entity.Discipline;
import domain.entity.Task;

import java.util.List;

public interface TaskRepository {
    Task create (Task task);
    List<Task> findByDiscipline(Discipline discipline);

    Task update(Task task);

    List<Task> getTasks();
}
