package data.repository.indatabase;

import data.repository.Storage;
import data.storage.SQLiteStorage;
import data.storage.dto.DisciplineDTO;
import data.storage.dto.TaskDTO;
import domain.entity.Discipline;
import domain.entity.Task;
import domain.port.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class InDBTaskRepository implements TaskRepository {
    private Storage storage;
    private List<Task> tasks = new ArrayList<>();

    public InDBTaskRepository(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Task create(Task task) {
        Task newTask = this.storage.addTask(new TaskDTO(task)).toTask(task.getDiscipline());
        this.tasks.add(newTask);
        return newTask;

    }

    @Override
    public List<Task> findByDiscipline(Discipline discipline) {
        List<Task> findedTasks = new ArrayList<>();
        for (Task task: tasks
             ) {
            if (task.getDiscipline().getId()==discipline.getId()){
                findedTasks.add(task);
            }
        }
        return findedTasks;
    }

    @Override
    public Task update(Task task) {
        storage.updateTask(new TaskDTO(task));
        return task;
    }

    @Override
    public List<Task> getAll() {
        List<TaskDTO> tasksDTO = storage.readAllTasks();
        List<DisciplineDTO> disciplinesDTO = storage.readAllDisciplines();
        List<Discipline> disciplines = new ArrayList<>();
        tasks = new ArrayList<>();
        for (int i = 0; i < disciplinesDTO.size(); i++) {
            disciplines.add(disciplinesDTO.get(i).toDiscipline());
        }

        for (int i = 0; i < tasksDTO.size(); i++) {
            int disciplineId = tasksDTO.get(i).getDiscipline_id();
            tasks.add(tasksDTO.get(i).toTask(findById(disciplines,disciplineId)));
        }
        return tasks;
    }

    private Discipline findById(List<Discipline> disciplines, int id){
        for (int i = 0; i < disciplines.size(); i++) {
            if(disciplines.get(i).getId()==id){
                return disciplines.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Task> getAllUnclosed() {
        List<Task> unclosedTasks = new ArrayList<>();
        for (Task task: tasks
             ) {
            if (task.isClosed()==false){
                unclosedTasks.add(task);
            }
        }
        return unclosedTasks;
    }

    @Override
    public boolean delete(Task task) {

        boolean result = storage.deleteTask(new TaskDTO(task));
        tasks.remove(task);
        return result;
    }
}
