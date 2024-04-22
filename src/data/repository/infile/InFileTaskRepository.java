package data.repository.infile;

import data.storage.InFileIDGenerator;
import data.storage.InFileTaskStorage;
import data.storage.dto.TaskDTO;
import domain.entity.Discipline;
import domain.entity.Task;
import domain.port.DisciplineRepository;
import domain.port.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class InFileTaskRepository implements TaskRepository {
    private DisciplineRepository disciplineRepository;
    private InFileTaskStorage taskStorage;
    private List<Task> tasks = new ArrayList<>();

    public InFileTaskRepository(InFileTaskStorage taskstorage, DisciplineRepository disciplineRepository){
       this.taskStorage =taskstorage;
       this.disciplineRepository=disciplineRepository;
    }

    @Override
    public Task create(Task task) {
        TaskDTO taskDTO = new TaskDTO(task,task.getDiscipline().getId());
        Task newTask =this.taskStorage.save(taskDTO).toTask(task.getDiscipline());
        this.tasks.add(newTask);
        return newTask;
    }
    @Override
    public List<Task> getTasks() {
        List<TaskDTO> tasksDTO = taskStorage.readAll();
        tasks = new ArrayList<>();
        for (int i = 0; i < tasksDTO.size(); i++) {
            int disciplineId = tasksDTO.get(i).getDiscipline_id();
            tasks.add(tasksDTO.get(i).toTask(disciplineRepository.getDisciplineByID(disciplineId)));
        }
        return tasks;
    }

    @Override
    public Task update(Task task) {
        List<TaskDTO> tasksDTO = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            tasksDTO.add(new TaskDTO(tasks.get(i),tasks.get(i).getDiscipline().getId()));
        }
        taskStorage.saveAll(tasksDTO);
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
        taskStorage.saveAll(tasksDTO);
        return removed;
    }
}
