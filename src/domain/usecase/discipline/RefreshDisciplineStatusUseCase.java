package domain.usecase.discipline;

import domain.entity.Discipline;
import domain.entity.Task;
import domain.port.DisciplineRepository;
import domain.port.TaskRepository;

import java.util.List;

public class RefreshDisciplineStatusUseCase {
    private final DisciplineRepository disciplineRepository;
    private final TaskRepository taskRepository;
    public RefreshDisciplineStatusUseCase(DisciplineRepository disciplineRepository, TaskRepository taskRepository){
        this.disciplineRepository = disciplineRepository;
        this.taskRepository = taskRepository;

    }
    public Discipline invoke(final Discipline discipline){
       if(checkTasks(discipline)){
           discipline.setClosed(true);
           disciplineRepository.update(discipline);
       }else{
           discipline.setClosed(false);
       }
       disciplineRepository.update(discipline);
       return discipline;
    }
    private boolean checkTasks(final Discipline discipline){
        boolean flag = true;
        List<Task> tasksByDiscipline = taskRepository.findByDiscipline(discipline);
        for (int i = 0; i < tasksByDiscipline.size(); i++) {
            if(!tasksByDiscipline.get(i).isClosed()){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
