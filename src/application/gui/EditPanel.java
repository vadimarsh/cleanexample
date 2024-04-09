package application.gui;

import application.config.GUISwingConfig;
import domain.entity.Task;
import domain.usecase.discipline.CreateDisciplineUseCase;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import domain.usecase.task.CreateTaskUseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPanel extends JPanel {
    MainWindow mainWindow;
    JButton butConfirm;
    JTextField tfTaskTitle;
    JTextField tfSemestr;
    CreateDisciplineUseCase createDisciplineUseCase;
    GetAllDisciplinesUseCase getAllDisciplinesUseCase;
    CreateTaskUseCase createTaskUseCase;

    GUISwingConfig guiSwingConfig;

    public EditPanel(MainWindow frame){
        mainWindow = frame;
        guiSwingConfig = frame.defaultConfig;
        createDisciplineUseCase = guiSwingConfig.createDiscipline();
        createTaskUseCase = guiSwingConfig.createTask();
        getAllDisciplinesUseCase = guiSwingConfig.getAllDiscliplines();
        init();
        placeComponents();
        setControllers();
    }

    private void setControllers() {
        butConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = new Task(5, tfTaskTitle.getText(),false, 100L,getAllDisciplinesUseCase.invoke().get(0));
                createTaskUseCase.invoke(task);
                mainWindow.tableModel.fireTableDataChanged();
                //                Discipline discipline = new Discipline(0, tfDisciplineTitle.getText(),false,Integer.parseInt(tfSemestr.getText()));
//                createDisciplineUseCase.invoke(discipline);
            }
        });
    }

    private void placeComponents() {
        BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.add(tfTaskTitle);
        this.add(tfSemestr);
        JPanel jPanel = new JPanel();
        FlowLayout layout = (FlowLayout) jPanel.getLayout();
        layout.setAlignment(FlowLayout.CENTER);
        jPanel.add(butConfirm);
        this.add(jPanel);
    }

    private void init() {
        butConfirm = new JButton("Добавить");
        tfTaskTitle = new JTextField();
        tfSemestr = new JTextField();
    }

}
