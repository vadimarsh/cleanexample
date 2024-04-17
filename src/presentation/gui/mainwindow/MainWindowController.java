package presentation.gui.mainwindow;

import presentation.config.GUISwingConfig;
import domain.entity.Discipline;
import domain.entity.Task;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import domain.usecase.task.CreateTaskUseCase;
import presentation.gui.discwindow.DisciplinesWindowController;
import presentation.gui.discwindow.DisciplinesWindowView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainWindowController {
    private MainWindowView view;
    private MainTableModel mainTableModel;

    private GUISwingConfig config;

    private GetAllDisciplinesUseCase getAllDisciplinesUseCase;
    private CreateTaskUseCase createTaskUseCase;

    public MainWindowController(GUISwingConfig guiSwingConfig, MainWindowView mainWindowView){
        this.config = guiSwingConfig;
        this.view = mainWindowView;

        createTaskUseCase = guiSwingConfig.createTask();
        getAllDisciplinesUseCase = guiSwingConfig.getAllDiscliplines();


        setTableModel();
        setDisciplinesList();
        setControllers();

    }

    private void setDisciplinesList() {
        view.getjlDiscipline().removeAllItems();
        List<Discipline> allDisciplines = getAllDisciplinesUseCase.invoke();
        for (int i = 0; i < allDisciplines.size(); i++) {
            view.getjlDiscipline().addItem(allDisciplines.get(i));
        }

    }

    public void setControllers(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTaskBtClick();
            }
        };
        view.getButAddTask().addActionListener(actionListener);

        view.getMiDisciplines().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisciplinesWindowController(config,new DisciplinesWindowView(view.getMainFrame()));
            }
        });
        view.getMainFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("window activated");
                setTableModel();
                setDisciplinesList();
            }
        });

    }

    private void createTaskBtClick() {
        Discipline discipline = (Discipline) getView().getjlDiscipline().getSelectedItem();
        Task task = new Task(5, view.getTfTaskTitle().getText(),false, 100L,discipline);
        createTaskUseCase.invoke(task);
        mainTableModel.fireTableDataChanged();
    }

    public void setTableModel(){
        mainTableModel = new MainTableModel(config);
        view.getTableTasks().setModel(mainTableModel);
    }


    public MainWindowView getView() {
        return view;
    }

    public void setView(MainWindowView view) {
        this.view = view;
    }

}
