package presentation.gui.mainwindow;

import domain.entity.Discipline;
import presentation.gui.components.JDateCellEditor;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainWindowView {
    private JMenuBar mainWindowMenuBar;
    private JMenu mainWindowMenu;
    private JMenuItem miDisciplines;
    private JCheckBoxMenuItem miUnclosedTasks;

    private final JFrame mainFrame;
    private JTable tableTasks;
    private JButton butAddTask;

    private JTextField tfTaskTitle;
    private JFormattedTextField tfDeadLine;
    private JComboBox<Discipline> cbDiscipline;

    public MainWindowView(){
        mainFrame = new JFrame("Список задач");

        initComponents();
        placeComponents();

        mainFrame.setSize(600,350);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    private void initComponents(){
        tableTasks = new JTable();
        tableTasks.setDefaultEditor(Date.class,new JDateCellEditor());

        mainWindowMenuBar = new JMenuBar();
        mainWindowMenu = new JMenu("Меню");
        miDisciplines = new JMenuItem("Дисциплины");
        miUnclosedTasks = new JCheckBoxMenuItem("Незакрытые задачи");

        butAddTask = new JButton("Добавить");
        tfTaskTitle = new JTextField(40);
        DateFormat date = new SimpleDateFormat("dd.MM.yyyy"); // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(date);

        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);

        tfDeadLine = new JFormattedTextField(dateFormatter);
        tfDeadLine.setColumns(10);
        tfDeadLine.setValue(new Date());
        tfDeadLine.setPreferredSize(new Dimension(30,30));
        cbDiscipline = new JComboBox();

    }

    private void placeComponents() {

        mainWindowMenu.add(miDisciplines);
        mainWindowMenu.add(miUnclosedTasks);
        mainWindowMenuBar.add(mainWindowMenu);
        mainFrame.setJMenuBar(mainWindowMenuBar);
        JLabel jLabelTask = new JLabel("Задача:");
        JLabel jLabelDiscipline = new JLabel("Предмет:");
        JLabel jLabelDeadline = new JLabel("Дедлайн:");


        Box taskTitleBox= new Box(BoxLayout.X_AXIS);
        taskTitleBox.add(Box.createRigidArea(new Dimension(40,0)));
        taskTitleBox.add(jLabelTask);
        taskTitleBox.add(Box.createRigidArea(new Dimension(10,0)));
        taskTitleBox.add(tfTaskTitle);
        taskTitleBox.add(Box.createRigidArea(new Dimension(40,0)));

        Box taskDetailsBox= new Box(BoxLayout.X_AXIS);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(40,0)));
        taskDetailsBox.add(jLabelDiscipline);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(10,0)));
        taskDetailsBox.add(cbDiscipline);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(10,0)));
        taskDetailsBox.add(jLabelDeadline);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(10,0)));
        taskDetailsBox.add(tfDeadLine);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(10,0)));
        taskDetailsBox.add(butAddTask);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(40,0)));


        Box wrapBox = new Box(BoxLayout.Y_AXIS);
        wrapBox.add(Box.createVerticalStrut(8));
        wrapBox.add(taskTitleBox);
        wrapBox.add(Box.createVerticalStrut(8));
        wrapBox.add(taskDetailsBox);
        wrapBox.add(Box.createVerticalStrut(8));

        mainFrame.getContentPane().add(new JScrollPane(tableTasks));
        mainFrame.getContentPane().add(wrapBox, BorderLayout.SOUTH);
    }
    public JFrame getMainFrame() {
        return mainFrame;
    }
    public JTable getTableTasks() {
        return tableTasks;
    }

    public JButton getButAddTask() {
        return butAddTask;
    }

    public JTextField getTfTaskTitle() {
        return tfTaskTitle;
    }

    public JComboBox getjlDiscipline() {
        return cbDiscipline;
    }

    public JMenuItem getMiDisciplines() {
        return miDisciplines;
    }
    public JCheckBoxMenuItem getMiUnclosedTasks() {
        return miUnclosedTasks;
    }

    public JFormattedTextField TfDeadLine() { return tfDeadLine;
    }
}
