import data.storage.SQLiteStorage;
import data.storage.dto.DisciplineDTO;
import data.storage.dto.TaskDTO;
import domain.entity.Discipline;
import domain.entity.Task;
import presentation.config.GUISwingConfig;
import presentation.gui.mainwindow.MainWindowController;
import presentation.gui.mainwindow.MainWindowView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // DefaultConfig defaultConfig = new DefaultConfig();

        //SQLiteStorage.getInstance().addDiscipline(new DisciplineDTO(new Discipline(-1,"Базы данных",false,4)));
       // SQLiteStorage.getInstance().addTask(new TaskDTO(-1,"Лабораторная №1","10.04.2025","Открыта",1));

        /* List<DisciplineDTO> disciplineDTOS = SQLiteStorage.getInstance().readAllDisciplines();
        for (DisciplineDTO discipline: disciplineDTOS
             ) {
            Discipline discipline1 = discipline.toDiscipline();
            System.out.println(discipline1.toString());
            discipline1.setClosed(true);
            SQLiteStorage.getInstance().updateDiscipline(new DisciplineDTO(discipline1));
        }
        List<TaskDTO> tasksDTOS = SQLiteStorage.getInstance().readAllTasks();
        for (TaskDTO task: tasksDTOS
        ) {
            System.out.println(task.toTask(disciplineDTOS.get(0).toDiscipline()).toString());

        }
        SQLiteStorage.getInstance().closeDB();
*/
        GUISwingConfig swingConfig = new GUISwingConfig();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindowController(swingConfig, new MainWindowView());
            }
        });


        /* CreateTaskUseCase createTask = defaultConfig.createTask();
        CreateDisciplineUseCase createDiscipline = defaultConfig.createDiscipline();
        GetAllDisciplinesUseCase getAllDisciplinesUseCase = defaultConfig.getAllDiscliplines();
        GetAllTasksByDisciplineUseCase getAllTasksByDisciplineUseCase = defaultConfig.getAllTasksByDisclipline();
        GetAllTasksUseCase getAllTasksUseCase = defaultConfig.getAllTasks();


        Discipline discipline1 = new Discipline(0, "ООП", false, 4);
        Discipline discipline2 = new Discipline(1, "Базы данных", false, 5);

        Task task1, task2, task3 = null;

       try {
           task1 = new Task(0, "Лаб.раб №1", false, DateFormat.getDateInstance(DateFormat.SHORT).parse("12.05.2024").getTime(), discipline1);
           task2 = new Task(0, "Лаб.раб №2", false, DateFormat.getDateInstance(DateFormat.SHORT).parse("10.05.2024").getTime(), discipline1);
           task3 = new Task(0, "Лаб.раб №1", false, DateFormat.getDateInstance(DateFormat.SHORT).parse("25.04.2024").getTime(), discipline2);
       } catch (ParseException e) {
           throw new RuntimeException(e);
        }
        createDiscipline.invoke(discipline1);
        createDiscipline.invoke(discipline2);

        createTask.invoke(task1);
        createTask.invoke(task2);
        createTask.invoke(task3);

        List<Discipline> disciplines = getAllDisciplinesUseCase.invoke();
        for (int i = 0; i < disciplines.size(); i++) {
            System.out.println(disciplines.get(i));


        List<Task> tasks = getAllTasksByDisciplineUseCase.invoke(discipline1);
        for (int i = 0; i < tasks.size(); i++) {
           System.out.println(tasks.get(i));
        }

        */

    }
}