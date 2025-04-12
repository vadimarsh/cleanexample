package presentation.gui.mainwindow;

import domain.entity.Discipline;
import domain.entity.Task;
import presentation.gui.components.JDateCellEditor;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

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
    private JComboBox cbDiscipline;

    private JPopupMenu popupMenu;
    private JMenuItem miCancelTask;

    public MainWindowView() {
        mainFrame = new JFrame("Список задач");

        initComponents();
        placeComponents();

        mainFrame.setSize(600, 350);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    private void initComponents() {
        tableTasks = new JTable();
        tableTasks.setDefaultEditor(Date.class, new JDateCellEditor());

        mainWindowMenuBar = new JMenuBar();
        mainWindowMenu = new JMenu("Меню");
        miDisciplines = new JMenuItem("Дисциплины");
        miUnclosedTasks = new JCheckBoxMenuItem("Незакрытые задачи");

        popupMenu = new JPopupMenu();
        miCancelTask = new JMenuItem("Отменить задачу");


        butAddTask = new JButton("Добавить");
        tfTaskTitle = new JTextField(40);
        DateFormat date = new SimpleDateFormat("dd.MM.yyyy"); // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(date);

        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);

        tfDeadLine = new JFormattedTextField(dateFormatter);
        tfDeadLine.setColumns(10);
        tfDeadLine.setValue(new Date());
        tfDeadLine.setPreferredSize(new Dimension(30, 30));
        cbDiscipline = new JComboBox();
        cbDiscipline.setPreferredSize(new Dimension(200, 10));

    }

    private void placeComponents() {

        popupMenu.add(miCancelTask);

        mainWindowMenu.add(miDisciplines);
        mainWindowMenu.add(miUnclosedTasks);
        mainWindowMenuBar.add(mainWindowMenu);
        mainFrame.setJMenuBar(mainWindowMenuBar);
        JLabel jLabelTask = new JLabel("Задача:");
        JLabel jLabelDiscipline = new JLabel("Предмет:");
        JLabel jLabelDeadline = new JLabel("Дедлайн:");


        Box taskTitleBox = new Box(BoxLayout.X_AXIS);
        taskTitleBox.add(Box.createRigidArea(new Dimension(40, 0)));
        taskTitleBox.add(jLabelTask);
        taskTitleBox.add(Box.createRigidArea(new Dimension(10, 0)));
        taskTitleBox.add(tfTaskTitle);
        taskTitleBox.add(Box.createRigidArea(new Dimension(40, 0)));

        Box taskDetailsBox = new Box(BoxLayout.X_AXIS);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(40, 0)));
        taskDetailsBox.add(jLabelDiscipline);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(10, 0)));
        taskDetailsBox.add(cbDiscipline);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(10, 0)));
        taskDetailsBox.add(jLabelDeadline);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(10, 0)));
        taskDetailsBox.add(tfDeadLine);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(10, 0)));
        taskDetailsBox.add(butAddTask);
        taskDetailsBox.add(Box.createRigidArea(new Dimension(40, 0)));


        Box wrapBox = new Box(BoxLayout.Y_AXIS);
        wrapBox.add(Box.createVerticalStrut(8));
        wrapBox.add(taskTitleBox);
        wrapBox.add(Box.createVerticalStrut(8));
        wrapBox.add(taskDetailsBox);
        wrapBox.add(Box.createVerticalStrut(8));

        mainFrame.getContentPane().add(new JScrollPane(tableTasks));
        mainFrame.getContentPane().add(wrapBox, BorderLayout.SOUTH);


        tableTasks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPopup(e);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            void showPopup(MouseEvent e) {
                if (tableTasks.getSelectedRow() > -1) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });


    }

    public JFrame getMainFrame() {
        return mainFrame;
    }


    public String getTaskTitle() {
        return tfTaskTitle.getText();
    }

    public Discipline getSelectedDiscipline() {
        ComboBoxModel comboBoxModel = cbDiscipline.getModel();
        Discipline discipline = ((DisciplComboBoxModel) comboBoxModel).getSelectedDiscipline();
        return discipline;
    }
    public Task getSelectedTask(){
        MainTableModel model = (MainTableModel)this.tableTasks.getModel();
        return model.getSelectedTask(this.tableTasks.getSelectedRow());
    }

    public boolean isUnclosedTasksChecked() {
        return miUnclosedTasks.getState();
    }

    public long getDeadlineValue() throws ParseException {
        long datetime;
        datetime = DateFormat.getDateInstance(DateFormat.SHORT).parse(this.tfDeadLine.getText()).getTime();

        return datetime;
    }


    public void showMsgDialog(String msg) {
        JDialog dialog = new JDialog(this.mainFrame, "Внимание", true);
        dialog.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialog.add(new JLabel(msg));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(this.mainFrame);
        dialog.setVisible(true);
    }

    public void setAddTaskListener(ActionListener buttonListener) {
        this.butAddTask.addActionListener(buttonListener);
    }

    public void setShowDisciplinesListener(ActionListener menuListener) {
        this.miDisciplines.addActionListener(menuListener);
    }

    public void setShowUnclosedTasksSwitchListener(ActionListener menuListener) {
        this.miUnclosedTasks.addActionListener(menuListener);
    }

    public void setDisciplinesList(List<Discipline> allDisciplines) {
        DisciplComboBoxModel disciplComboBoxModel = new DisciplComboBoxModel(allDisciplines);
        this.cbDiscipline.setModel(disciplComboBoxModel);
        this.cbDiscipline.revalidate();
    }

    public void setWindowListener(WindowListener windowListener) {
        this.mainFrame.addWindowListener(windowListener);
    }

    public void setTableTasksModel(MainTableModel mainTableModel) {
        this.tableTasks.setModel(mainTableModel);
    }

    public void setCancelTaskListener(ActionListener buttonListener) {
        miCancelTask.addActionListener(buttonListener);

    }
}