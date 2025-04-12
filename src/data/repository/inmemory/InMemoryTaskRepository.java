package data.repository.inmemory;

import domain.entity.Discipline;
import domain.entity.Task;
import domain.port.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public Task create(Task task) {
            int id = this.tasks.size();
            task = new Task(id,task.getTitle(),task.isClosed(),task.getDeadline(),task.getDiscipline());
            tasks.add(task);
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
    public Task update(Task task) {
        Task updatedTask = new Task(task.getId(),task.getTitle(),task.isClosed(),task.getDeadline(),task.getDiscipline());
        int oldTaskIndex = tasks.indexOf(task);
        tasks.set(oldTaskIndex,updatedTask);
        return updatedTask;
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public List<Task> getAllUnclosed() {
        List<Task> unclosedTasks= new ArrayList();
        for (int i = 0; i < tasks.size(); i++) {
            if(!tasks.get(i).isClosed()){
                unclosedTasks.add(tasks.get(i));
            }
        }
        return unclosedTasks;
    }

    @Override
    public boolean delete(Task task) {
        tasks.remove(task);
        return tasks.remove(task);
    }
}
