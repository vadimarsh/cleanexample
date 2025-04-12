package presentation.gui.mainwindow;

import domain.usecase.discipline.RefreshDisciplineStatusUseCase;
import domain.usecase.task.*;
import presentation.config.GUISwingConfig;
import domain.entity.Discipline;
import domain.entity.Task;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import presentation.gui.discwindow.DisciplinesWindowController;
import presentation.gui.discwindow.DisciplinesWindowView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;

public class MainWindowController {
    private GUISwingConfig config;
    private final MainWindowView view;
    private MainTableModel mainTableModel;

    // слушатели
    private MenuListener menuListener;
    private ButtonListener buttonListener;
    private WindowListener windowListener;

    // доступные посредством окна UseCase
    private GetAllDisciplinesUseCase getAllDisciplinesUseCase;
    private CreateTaskUseCase createTaskUseCase;
    private GetUnclosedTasks getUnclosedTasksUseCase;
    private GetAllTasksUseCase getAllTasksUseCase;
    private MarkDoneTaskUseCase markDoneTaskUseCase;
    private RefreshDisciplineStatusUseCase refreshDisciplineStatusUseCase;
    private SetDeadLineUseCase setDeadLineUseCase;
    private CancelTaskUseCase cancelTaskUseCase;

    public MainWindowController(GUISwingConfig config, MainWindowView mainWindowView){
        this.config = config;
        this.view = mainWindowView;
        this.menuListener = new MenuListener();
        this.windowListener = new WindowListener();
        this.buttonListener = new ButtonListener();


        createTaskUseCase = config.createTask();
        getAllDisciplinesUseCase = config.getAllDiscliplines();
        getUnclosedTasksUseCase = config.getUnclosedTasks();
        getAllTasksUseCase = config.getAllTasks();
        markDoneTaskUseCase = config.markDoneTask();
        refreshDisciplineStatusUseCase = config.refreshDisciplineStatus();
        setDeadLineUseCase = config.setDeadLine();
        cancelTaskUseCase = config.cancelTaskUseCase();
        refreshTableModel();
        setDisciplinesList();
        setControllers();
    }

    private void setDisciplinesList() {
        List<Discipline> allDisciplines = getAllDisciplinesUseCase.invoke();
        view.setDisciplinesList(allDisciplines);
    }

    private void setControllers(){
        view.setAddTaskListener(buttonListener);
        view.setShowDisciplinesListener(menuListener);
        view.setShowUnclosedTasksSwitchListener(menuListener);
        view.setWindowListener(windowListener);

        view.setCancelTaskListener(menuListener);
    }

    private void createTaskBtClick() {
            Task task = null;
            try {
                task = new Task(0, view.getTaskTitle(), false, view.getDeadlineValue(), view.getSelectedDiscipline());

                task = createTaskUseCase.invoke(task); // добавляем новую задачу
                //вызываем функцию бизнес-логики "обновить статус дисциплины", чтобы опять пометить дисциплину как нерешенную, на тот случай если она была уже закрыта
                refreshDisciplineStatusUseCase.invoke(task.getDiscipline());
                mainTableModel.fireTableDataChanged();
            } catch (ParseException e) {
                System.err.println("неверный формат даты");
                view.showMsgDialog("Неверный формат даты");
            } catch (RuntimeException ex){
                view.showMsgDialog("Следует выбрать дисциплину");
            }

    }

    public void refreshTableModel(){
        if(view.isUnclosedTasksChecked()) {
            mainTableModel = new MainTableModel(this, getUnclosedTasksUseCase.invoke());
        }
        else{
            mainTableModel = new MainTableModel(this, getAllTasksUseCase.invoke());
        }
        view.setTableTasksModel(mainTableModel);
    }


    public MainWindowView getView() {
        return view;
    }

    public boolean closeTask(Task task){
        if (!task.isClosed()) {
            task=markDoneTaskUseCase.invoke(task); //вызываем функцию бизнес-логики "закрыть задачу"
            Discipline discipline = task.getDiscipline();
            System.out.println("close"+discipline);
            //вызываем функцию бизнес-логики "обновить статус дисциплины" на тот случай если все задачи в этой дисциплине были уже решены
            refreshDisciplineStatusUseCase.invoke(discipline);
            refreshTableModel();
            return true;
        }
        return false;
    }

    public void updateDeadlineTask(Task task, long deadline) {
        task=setDeadLineUseCase.invoke(task,deadline);
        refreshTableModel();
    }


    private class MenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = e.getActionCommand();
            if (o.equals("Незакрытые задачи")) {
                refreshTableModel();
            } else if (o.equals("Дисциплины")) {
                new DisciplinesWindowController(config, new DisciplinesWindowView(view.getMainFrame()));
            } else if (o.equals("Отменить задачу")) {
                Task canceledTask = view.getSelectedTask();
                cancelTaskUseCase.invoke(canceledTask);
                Discipline discipline = canceledTask.getDiscipline();
                refreshDisciplineStatusUseCase.invoke(discipline);
                refreshTableModel();
            }
        }
    }

    private class ButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                createTaskBtClick();
            }
    }
    private class WindowListener extends WindowAdapter{
        @Override
        public void windowActivated(WindowEvent e) {
            refreshTableModel();
            setDisciplinesList();
        }
    }
}
