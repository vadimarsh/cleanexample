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
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

public class MainWindowController {
    private GUISwingConfig config;
    private final MainWindowView view;
    private MainTableModel mainTableModel;
    private boolean showClosedTasks; // флажок для определеления состояния таблицы

    // слушатели
    private MenuListener menuListener;
    private ButtonListener buttonListener;

    // доступные посредством окна UseCase
    private GetAllDisciplinesUseCase getAllDisciplinesUseCase;
    private CreateTaskUseCase createTaskUseCase;
    private GetUnclosedTasks getUnclosedTasksUseCase;
    private GetAllTasksUseCase getAllTasksUseCase;
    private MarkDoneTaskUseCase markDoneTaskUseCase;
    private RefreshDisciplineStatusUseCase refreshDisciplineStatusUseCase;
    private SetDeadLineUseCase setDeadLineUseCase;

    public MainWindowController(GUISwingConfig config, MainWindowView mainWindowView){
        this.config = config;
        this.view = mainWindowView;
        this.menuListener = new MenuListener();
        this.buttonListener = new ButtonListener();
        this.showClosedTasks = false;

        createTaskUseCase = config.createTask();
        getAllDisciplinesUseCase = config.getAllDiscliplines();
        getUnclosedTasksUseCase = config.getUnclosedTasks();
        getAllTasksUseCase = config.getAllTasks();
        markDoneTaskUseCase = config.markDoneTask();
        refreshDisciplineStatusUseCase = config.refreshDisciplineStatus();
        setDeadLineUseCase = config.setDeadLine();

        refreshTableModel();
        setDisciplinesList();
        setControllers();
    }

    private void setDisciplinesList() {
        //view.getjlDiscipline().removeAllItems();

        List<Discipline> allDisciplines = getAllDisciplinesUseCase.invoke();
        view.getjlDiscipline().setModel(new DisciplComboBoxModel(allDisciplines));
        view.getjlDiscipline().revalidate();
        //for (int i = 0; i < allDisciplines.size(); i++) {
        //    view.getjlDiscipline().addItem(allDisciplines.get(i).getName());
        //}

    }

    private void setControllers(){
        view.getButAddTask().addActionListener(buttonListener);
        view.getMiDisciplines().addActionListener(menuListener);
        view.getMiUnclosedTasks().addActionListener(menuListener);

        view.getMainFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                refreshTableModel();
                setDisciplinesList();
            }
        });

    }

    private void createTaskBtClick() {
        DisciplComboBoxModel model = (DisciplComboBoxModel) getView().getjlDiscipline().getModel();
        Discipline discipline = model.getSelectedDiscipline();
        if(discipline!=null) {
            Task task = null;
            try {
                task = new Task(0, view.getTfTaskTitle().getText(), false, DateFormat.getDateInstance(DateFormat.SHORT).parse(view.TfDeadLine().getText()).getTime(), discipline);
            } catch (ParseException e) {
                System.err.println("неверный формат даты");
            }
            task = createTaskUseCase.invoke(task); // добавляем новую задачу
            //вызываем функцию бизнес-логики "обновить статус дисциплины", чтобы опять пометить дисциплину как нерешенную, на тот случай если она была уже закрыта
            refreshDisciplineStatusUseCase.invoke(task.getDiscipline());
            mainTableModel.fireTableDataChanged();
        }
    }

    public void refreshTableModel(){
        if(showClosedTasks) {
            mainTableModel = new MainTableModel(this, getUnclosedTasksUseCase.invoke());
        }
        else{
            mainTableModel = new MainTableModel(this, getAllTasksUseCase.invoke());
        }
        view.getTableTasks().setModel(mainTableModel);
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
            Object o = e.getSource();
            if (o.equals(getView().getMiUnclosedTasks())) {
                showClosedTasks=getView().getMiUnclosedTasks().getState();
                refreshTableModel();
            } else if (o.equals(getView().getMiDisciplines())) {
                new DisciplinesWindowController(config, new DisciplinesWindowView(view.getMainFrame()));
            }
        }
    }

    private class ButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                createTaskBtClick();
            }
    }
}
