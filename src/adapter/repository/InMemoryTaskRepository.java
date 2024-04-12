package adapter.repository;

import domain.entity.Discipline;
import domain.entity.Task;
import domain.port.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public Task create(Task task) {
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
        task.setClosed(true);
        return task;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }
}
