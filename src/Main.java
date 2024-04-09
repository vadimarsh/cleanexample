import application.config.GUISwingConfig;
import application.gui.MainWindowController;
import application.gui.MainWindowView;
import domain.entity.Discipline;
import domain.entity.Task;
import domain.usecase.discipline.CreateDisciplineUseCase;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import domain.usecase.task.CreateTaskUseCase;
import domain.usecase.task.GetAllTasksByDisciplineUseCase;
import domain.usecase.task.GetAllTasksUseCase;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
       // DefaultConfig defaultConfig = new DefaultConfig();

        GUISwingConfig defaultConfig = new GUISwingConfig();
        CreateTaskUseCase createTask = defaultConfig.createTask();
        CreateDisciplineUseCase createDiscipline = defaultConfig.createDiscipline();
        GetAllDisciplinesUseCase getAllDisciplinesUseCase = defaultConfig.getAllDiscliplines();
        GetAllTasksByDisciplineUseCase getAllTasksByDisciplineUseCase = defaultConfig.getAllTasksByDisclipline();
        GetAllTasksUseCase getAllTasksUseCase = defaultConfig.getAllTasks();


        Discipline discipline1 = new Discipline(0,"ООП", false, 4);
        Discipline discipline2 = new Discipline(1,"Базы данных", false, 5);

        Task task1 = new Task(0,"Лаб.раб №1", false,Date.parse("Sat, 12 Aug 1995 13:30:00 GMT"),discipline1);
        Task task2 = new Task(1,"Лаб.раб №2", false, Date.parse("Sat, 12 Aug 1995 13:30:00 GMT"),discipline1);
        Task task3 = new Task(2,"Лаб.раб №1", false, Date.parse("Sat, 12 Aug 1995 13:30:00 GMT"),discipline2);

        createDiscipline.invoke(discipline1);
        createDiscipline.invoke(discipline2);

        createTask.invoke(task1);
        createTask.invoke(task2);
        createTask.invoke(task3);

        List<Discipline> disciplines = getAllDisciplinesUseCase.invoke();
        for (int i = 0; i < disciplines.size(); i++) {
            System.out.println(disciplines.get(i));
        }

        List<Task> tasks = getAllTasksByDisciplineUseCase.invoke(discipline1);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i));
        }

        new MainWindowController(defaultConfig, new MainWindowView());

    }
}