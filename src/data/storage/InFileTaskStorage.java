package data.storage;

import data.storage.dto.TaskDTO;
import domain.entity.Discipline;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InFileTaskStorage {
    private static final String TASKS_FNAME = "tasks.ps";
    private BufferedReader reader;
    private BufferedWriter writer;
    public List<TaskDTO> readAll(){
        List<TaskDTO> readedDisciplines = new ArrayList<>();
        TaskDTO task = null;
        try {
            reader = new BufferedReader(new FileReader(TASKS_FNAME));
            while (reader.ready()){
                String readedline =reader.readLine();
                String[] splitedLine = readedline.split("\\|");
                int taskID = Integer.parseInt(splitedLine[0]);
                String taskTitle = splitedLine[1];
                String taskDeadline = splitedLine[2];
                String taskStatusRaw = splitedLine[3];
                int taskDisciplineId = Integer.parseInt(splitedLine[4]);
                task = new TaskDTO(taskID,taskTitle,taskDeadline,taskStatusRaw,taskDisciplineId);
                readedDisciplines.add(task);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readedDisciplines;
    }
    public void saveAll(List<TaskDTO> tasks) {
        try {
            writer = new BufferedWriter(new FileWriter(TASKS_FNAME,false));
            for (TaskDTO task : tasks) {
                {
                    int taskID = task.getId();
                    String taskTitle = task.getName();
                    String taskDeadline = task.getDeadline();
                    String taskStatus = task.getStatus();
                    int disciplineId = task.getDiscipline_id();
                    writer.write(taskID+"|"+taskTitle+"|"+taskDeadline+"|"+taskStatus+"|"+disciplineId);
                    writer.newLine();
                }
            }
            writer.close();
            } catch(IOException e) {

            throw new RuntimeException(e);
        }
    }

    public void save(TaskDTO task) {
        int taskID = task.getId();
        String taskTitle = task.getName();
        String taskDeadline = task.getDeadline();
        String taskStatus = task.getStatus();
        int disciplineId = task.getDiscipline_id();

        try {
            writer = new BufferedWriter(new FileWriter(TASKS_FNAME,true));
            writer.write(taskID+"|"+taskTitle+"|"+taskDeadline+"|"+taskStatus+"|"+disciplineId);
            writer.newLine();
            writer.close();
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
