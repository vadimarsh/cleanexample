package application.gui;

import application.config.GUISwingConfig;
import domain.entity.Task;
import domain.usecase.discipline.CreateDisciplineUseCase;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import domain.usecase.task.CreateTaskUseCase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowController {
    private MainWindowView mainWindowView;
    private MainTableModel mainTableModel;
    private ActionListener actionListener;
    private GUISwingConfig config;
    private CreateDisciplineUseCase createDisciplineUseCase;
    private GetAllDisciplinesUseCase getAllDisciplinesUseCase;
    private CreateTaskUseCase createTaskUseCase;

    public MainWindowController(GUISwingConfig guiSwingConfig, MainWindowView mainWindowView){
        createDisciplineUseCase = guiSwingConfig.createDiscipline();
        createTaskUseCase = guiSwingConfig.createTask();
        getAllDisciplinesUseCase = guiSwingConfig.getAllDiscliplines();


        this.config = guiSwingConfig;
        this.mainWindowView = mainWindowView;
        setTableModel();
        setControllers();
    }
    public void setControllers(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTaskBtClick();
            }
        };
        mainWindowView.getButConfirm().addActionListener(actionListener);
    }

    private void createTaskBtClick() {
        Task task = new Task(5, mainWindowView.getTfTaskTitle().getText(),false, 100L,getAllDisciplinesUseCase.invoke().get(0));
        createTaskUseCase.invoke(task);
        mainTableModel.fireTableDataChanged();
    }

    public void setTableModel(){
        mainTableModel = new MainTableModel(config);
        mainWindowView.getjTable().setModel(mainTableModel);
    }


    public MainWindowView getMainWindowView() {
        return mainWindowView;
    }

    public void setMainWindowView(MainWindowView mainWindowView) {
        this.mainWindowView = mainWindowView;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
