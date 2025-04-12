package data.repository;

import data.storage.dto.DisciplineDTO;
import data.storage.dto.TaskDTO;

import java.util.List;

public interface Storage {
    List<TaskDTO> readAllTasks();

    TaskDTO addTask(TaskDTO task);

    TaskDTO updateTask(TaskDTO task);

    List<DisciplineDTO> readAllDisciplines();

    DisciplineDTO updateDiscipline(DisciplineDTO discipline);

    DisciplineDTO addDiscipline(DisciplineDTO discipline);

    boolean deleteTask(TaskDTO task);
}
