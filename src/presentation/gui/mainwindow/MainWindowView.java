package presentation.gui.mainwindow;

import presentation.gui.JDateCellEditor;

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
    private JPanel addTaskPanel;
    private JButton butAddTask;

    private JTextField tfTaskTitle;
    private JFormattedTextField tfDeadLine;
    private JComboBox cbDiscipline;






    public MainWindowView(){
        mainFrame = new JFrame("Список задач");

        initComponents();
        placeComponents();

        mainFrame.setSize(400,500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();
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

        addTaskPanel = new JPanel();

        butAddTask = new JButton("Добавить");
        tfTaskTitle = new JTextField();
        DateFormat date = new SimpleDateFormat("dd.MM.yyyy"); // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(date);
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        tfDeadLine = new JFormattedTextField(dateFormatter);
        tfDeadLine.setColumns(10);
        tfDeadLine.setValue(new Date());
        cbDiscipline = new JComboBox();
    }

    private void placeComponents() {

        mainWindowMenu.add(miDisciplines);
        mainWindowMenu.add(miUnclosedTasks);
        mainWindowMenuBar.add(mainWindowMenu);
        mainFrame.setJMenuBar(mainWindowMenuBar);

        BoxLayout boxLayout = new BoxLayout(addTaskPanel,BoxLayout.Y_AXIS);
        addTaskPanel.setLayout(boxLayout);
        addTaskPanel.add(tfTaskTitle);
        addTaskPanel.add(tfDeadLine);
        addTaskPanel.add(cbDiscipline);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(butAddTask);
        addTaskPanel.add(panel);

        mainFrame.getContentPane().add(new JScrollPane(tableTasks));
        mainFrame.getContentPane().add(addTaskPanel, BorderLayout.SOUTH);
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
