package presentation.gui.mainwindow;

import javax.swing.*;
import java.awt.*;

public class MainWindowView {
    private JMenuBar mainWindowMenuBar;
    private JMenu mainWindowMenu;
    private JMenuItem miDisciplines;
    private JFrame mainFrame;
    private JTable tableTasks;
    private JPanel addTaskPanel;
    private JButton butAddTask;
    private JTextField tfTaskTitle;
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

        mainWindowMenuBar = new JMenuBar();
        mainWindowMenu = new JMenu("Дисциплины");
        miDisciplines = new JMenuItem("Показать");

        addTaskPanel = new JPanel();

        butAddTask = new JButton("Добавить");
        tfTaskTitle = new JTextField();
        cbDiscipline = new JComboBox();
    }

    private void placeComponents() {

        mainWindowMenu.add(miDisciplines);
        mainWindowMenuBar.add(mainWindowMenu);
        mainFrame.setJMenuBar(mainWindowMenuBar);

        BoxLayout boxLayout = new BoxLayout(addTaskPanel,BoxLayout.Y_AXIS);
        addTaskPanel.setLayout(boxLayout);
        addTaskPanel.add(tfTaskTitle);
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

    public JPanel getAddTaskPanel() {
        return addTaskPanel;
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
}
