package data.repository.infile;

import data.storage.InFileStorage;
import data.storage.Storage;
import data.storage.dto.DisciplineDTO;
import data.storage.dto.TaskDTO;
import domain.entity.Discipline;
import domain.entity.Task;
import domain.port.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class InFileTaskRepository implements TaskRepository {
    private Storage storage;
    private List<Task> tasks = new ArrayList<>();
    public InFileTaskRepository(Storage storage){
       this.storage =storage;

    }

    @Override
    public Task create(Task task) {
        TaskDTO taskDTO = new TaskDTO(task,task.getDiscipline().getId());
        Task newTask =this.storage.addTask(taskDTO).toTask(task.getDiscipline());
        this.tasks.add(newTask);
        return newTask;
    }
    @Override
    public List<Task> getTasks() {
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
    public Task update(Task task) {
        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            tasksDTO.add(new TaskDTO(tasks.get(i),tasks.get(i).getDiscipline().getId()));
        }
        storage.saveAllTasks(tasksDTO);
        return task;
    }



    @Override
    public List<Task> findByDiscipline(Discipline discipline) {
        List<Task> finded = new ArrayList<>();
        for (Task task : tasks){
            if(task.getDiscipline().getId() == discipline.getId()){
                finded.add(task);
            }
        }
        return finded;

    }

    @Override
    public List<Task> getUnclosedTasks() {
        List<Task> unclosedTasks = new ArrayList();
        for (int i = 0; i < tasks.size(); i++) {
            if(!tasks.get(i).isClosed()){
                unclosedTasks.add(tasks.get(i));
            }
        }
        return unclosedTasks;
    }

    @Override
    public boolean delete(Task task) {
        boolean removed = tasks.remove(task);
        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            tasksDTO.add(new TaskDTO(tasks.get(i),tasks.get(i).getDiscipline().getId()));
        }
        storage.saveAllTasks(tasksDTO);
        return removed;
    }
}
