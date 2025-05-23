package data.storage;

import data.repository.Storage;
import data.storage.dto.DisciplineDTO;
import data.storage.dto.TaskDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InFileStorage implements Storage {
    private static final String TASKS_FNAME = "tasks.ps";
    private static final String DISCIPLINES_FNAME = "disciplines.ps";

    private InFileIDGenerator idTaskGenerator;
    private InFileIDGenerator idDiscipGenerator;

    public InFileStorage(InFileIDGenerator idDiscGenerator,  InFileIDGenerator idTaskGenerator) {
        this.idTaskGenerator = idTaskGenerator;
        this.idDiscipGenerator = idDiscGenerator;
        File taskFile = new File(TASKS_FNAME);
        if(!taskFile.isFile()){
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File discipFile = new File(TASKS_FNAME);
        if(!discipFile.isFile()){
            try {
                discipFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<TaskDTO> readAllTasks(){
        List<TaskDTO> readedTasks = new ArrayList<>();
        TaskDTO task = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(TASKS_FNAME))) {
            //reader = new BufferedReader(new FileReader(TASKS_FNAME));
            while (reader.ready()){
                String readedline =reader.readLine();
                String[] splitedLine = readedline.split("\\|");
                int taskID = Integer.parseInt(splitedLine[0]);
                String taskTitle = splitedLine[1];
                String taskDeadline = splitedLine[2];
                String taskStatusRaw = splitedLine[3];
                int taskDisciplineId = Integer.parseInt(splitedLine[4]);
                task = new TaskDTO(taskID,taskTitle,taskDeadline,taskStatusRaw,taskDisciplineId);
                readedTasks.add(task);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readedTasks;
    }

    @Override
    public TaskDTO addTask(TaskDTO task) {
        task.setId(idTaskGenerator.generate());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(TASKS_FNAME,true))) {
            writer.write(task.getId()+"|"+task.getName()+"|"+task.getDeadline()+"|"+task.getStatus()+"|"+task.getDiscipline_id());
            writer.newLine();
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
        return task;
    }

    @Override
    public TaskDTO updateTask(TaskDTO updatedTask) {
        List<TaskDTO> tasks = readAllTasks();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASKS_FNAME,false));){
            for (TaskDTO task : tasks) {
                {
                    if(updatedTask.getId()==task.getId()) {
                        writer.write(updatedTask.getId() + "|" + updatedTask.getName() + "|" + updatedTask.getDeadline() + "|" + updatedTask.getStatus() + "|" + updatedTask.getDiscipline_id());
                    }else {
                        writer.write(task.getId() + "|" + task.getName() + "|" + task.getDeadline() + "|" + task.getStatus() + "|" + task.getDiscipline_id());
                    }
                    writer.newLine();

                }
            }
        } catch(IOException e) {

            throw new RuntimeException(e);
        }
        return updatedTask;
    }

    @Override
    public List<DisciplineDTO> readAllDisciplines(){
        List<DisciplineDTO> readedDisciplines = new ArrayList<>();
        DisciplineDTO disciplineDTO;
        try (BufferedReader reader = new BufferedReader(new FileReader(DISCIPLINES_FNAME))){
            while (reader.ready()){
                String readedline =reader.readLine();
                String[] splitedLine = readedline.split("\\|");
                int disciplineID = Integer.parseInt(splitedLine[0]);
                String disciplineTitle = splitedLine[1];
                String disciplineSemestr = splitedLine[2];
                String disciplineStatusRaw = splitedLine[3];
                disciplineDTO = new DisciplineDTO(disciplineID,disciplineTitle,disciplineSemestr,disciplineStatusRaw);
                readedDisciplines.add(disciplineDTO);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readedDisciplines;
    }
    @Override
    public DisciplineDTO updateDiscipline(DisciplineDTO updatedDiscipline) {
        List<DisciplineDTO> disciplines = readAllDisciplines();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DISCIPLINES_FNAME,false))){
            for (DisciplineDTO discipline : disciplines) {
                {
                    if(discipline.getId()==updatedDiscipline.getId()){
                        writer.write(updatedDiscipline.getId()+"|"+updatedDiscipline.getName()+"|"+updatedDiscipline.getSemestr()+"|"+updatedDiscipline.getClosed());
                    }
                    else {
                        writer.write(discipline.getId()+"|"+discipline.getName()+"|"+discipline.getSemestr()+"|"+discipline.getClosed());
                    }
                    writer.newLine();
                }
            }
        } catch(IOException e) {

            throw new RuntimeException(e);
        }
        return updatedDiscipline;
    }

    @Override
    public DisciplineDTO addDiscipline(DisciplineDTO discipline) {
        discipline.setId(idDiscipGenerator.generate());
        System.out.println("new id = "+discipline.getId());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(DISCIPLINES_FNAME,true))){
            writer.write(discipline.getId() + "|" + discipline.getName() + "|" + discipline.getSemestr() + "|" + discipline.getClosed());
            writer.newLine();
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
        return discipline;
    }

    @Override
    public boolean deleteTask(TaskDTO deletedTask) {
        boolean flag = false;
        List<TaskDTO> tasks = readAllTasks();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASKS_FNAME,false));){
            for (TaskDTO task : tasks) {
                {
                    if(deletedTask.getId()!=task.getId()) {
                        writer.write(task.getId() + "|" + task.getName() + "|" + task.getDeadline() + "|" + task.getStatus() + "|" + task.getDiscipline_id());
                        writer.newLine();
                    }else{
                        flag = true;
                    }
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return flag;
    }
}
