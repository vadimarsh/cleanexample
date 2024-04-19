package data.repository.infile;

import data.storage.InFileDisciplineStorage;
import data.storage.InFileIDGenerator;
import data.storage.InFileTaskStorage;
import data.storage.dto.TaskDTO;
import domain.entity.Discipline;
import domain.entity.Task;
import domain.port.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class InFileTaskRepository implements TaskRepository {
   private InFileTaskStorage taskStorage;
   private InFileIDGenerator idGenerator;
   private List<Task> tasks = new ArrayList<>();

    public InFileTaskRepository(InFileTaskStorage taskstorage, InFileTaskStorage disciplineStorage, InFileIDGenerator idGenerator){
       this.taskStorage =taskstorage;
       this.idGenerator=idGenerator;
    }

    @Override
    public Task create(Task task) {
        Task newTask = new Task(idGenerator.generate(),task.getTitle(), task.isClosed(), task.getDeadline(),task.getDiscipline());
        this.tasks.add(task);
        TaskDTO taskDTO = new TaskDTO(task,task.getDiscipline().getId());
        this.taskStorage.save(taskDTO);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        List<TaskDTO> tasksDTO = taskStorage.readAll();
        tasks = new ArrayList<>();
        for (int i = 0; i < tasksDTO.size(); i++) {
            int disciplineId = tasksDTO.get(i).getDiscipline_id();
            tasks.add(tasksDTO.get(i).toTask());
        }
        return tasks;
    }

    @Override
    public void update(Discipline discipline) {
        tasks.set(tasks.indexOf(discipline),discipline);
        taskStorage.saveAll(tasks);
    }

    @Override
    public List<Task> findByDiscipline(Discipline discipline) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public List<Task> getUnclosedTasks() {
        return null;
    }

    @Override
    public boolean delete(Task task) {
        return false;
    }
}
