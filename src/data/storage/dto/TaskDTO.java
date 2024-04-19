package data.storage.dto;

import domain.entity.Discipline;
import domain.entity.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskDTO {
    private int id;
    private String name;
    private String closed;
    private String deadline;
    private int discipline_id;

    public TaskDTO(Task task, int discipline_id){
        this.id = task.getId();
        this.name = task.getTitle();
        if(task.isClosed()){
            this.closed = "Закрыта";
        }else{
            this.closed = "Открыта";
        }
         DateFormat date = new SimpleDateFormat("dd.MM.yyyy"); // Форматирующий объект даты
        this.deadline = date.format(new Date(this.deadline)).toString();
        this.discipline_id = discipline_id;
    }
    public TaskDTO(int id, String name, String deadline, String closed, int discipline_id) {
        this.id = id;
        this.name = name;
        this.closed = closed;
        this.deadline = deadline;
        this.discipline_id = discipline_id;
    }
    public Task toTask(Discipline discipline){
        Task task = null;
        try {
            boolean taskStatus = false;
            if(this.closed.equals("Закрыта")){
                taskStatus = true;
            }
             task = new Task(this.id,this.name,taskStatus,DateFormat.getDateInstance(DateFormat.SHORT).parse(this.deadline).getTime(), discipline);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return task;
    }
    public int getDiscipline_id() {
        return discipline_id;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return closed;
    }

    public String getDeadline() {
        return deadline;
    }
}
