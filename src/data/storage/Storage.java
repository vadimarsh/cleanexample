package data.storage;

import data.storage.dto.DisciplineDTO;
import data.storage.dto.TaskDTO;

import java.util.List;

public interface Storage {
    List<TaskDTO> readAllTasks();

    void saveAllTasks(List<TaskDTO> tasks);

    TaskDTO addTask(TaskDTO task);

    List<DisciplineDTO> readAllDisciplines();

    void saveAllDisciplines(List<DisciplineDTO> disciplines);

    DisciplineDTO addDiscipline(DisciplineDTO discipline);
}
