import presentation.config.GUISwingConfig;
import presentation.gui.mainwindow.MainWindowController;
import presentation.gui.mainwindow.MainWindowView;

public class Main {
    public static void main(String[] args) {
        // DefaultConfig defaultConfig = new DefaultConfig();
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