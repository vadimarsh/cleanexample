package presentation.gui.discwindow;

import javax.swing.*;
import java.awt.*;

public class DisciplinesWindowView {
    private final JDialog disciplinesFrame;
    private JTable tableDisciplines;
    private JPanel addDisciplinePanel;
    private JButton butAddDiscipline;
    private JTextField tfDisciplineTitle;
    private JTextField tfSemestr;


    public DisciplinesWindowView(JFrame parent){
        disciplinesFrame = new JDialog(parent,"Список предметов");

        initComponents();
        placeComponents();

        disciplinesFrame.setSize(300,300);
        disciplinesFrame.setLocationRelativeTo(null);
        disciplinesFrame.setResizable(false);
        disciplinesFrame.setVisible(true);
        disciplinesFrame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

    }

    private void placeComponents() {

        Box disciplineTitleBox = new Box(BoxLayout.X_AXIS);
        JLabel labelDisciplineTitle = new JLabel("Название:");
        disciplineTitleBox.add(Box.createRigidArea(new Dimension(20,0)));
        disciplineTitleBox.add(labelDisciplineTitle);
        disciplineTitleBox.add(Box.createRigidArea(new Dimension(10,0)));
        disciplineTitleBox.add(tfDisciplineTitle);
        disciplineTitleBox.add(Box.createRigidArea(new Dimension(20,0)));

        Box disciplineSemestrBox = new Box(BoxLayout.X_AXIS);
        JLabel labelDisciplineSemestr = new JLabel("Семестр:");
        disciplineSemestrBox.add(Box.createRigidArea(new Dimension(40,0)));
        disciplineSemestrBox.add(labelDisciplineSemestr);
        disciplineSemestrBox.add(Box.createRigidArea(new Dimension(10,0)));
        disciplineSemestrBox.add(tfSemestr);
        disciplineSemestrBox.add(Box.createRigidArea(new Dimension(40,0)));

        addDisciplinePanel.setLayout(new BoxLayout(addDisciplinePanel,BoxLayout.Y_AXIS));
        addDisciplinePanel.add(disciplineTitleBox);
        addDisciplinePanel.add(Box.createRigidArea(new Dimension(0,10)));
        addDisciplinePanel.add(disciplineSemestrBox);

        JPanel wrapButPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapButPanel.add(butAddDiscipline);
        addDisciplinePanel.add(wrapButPanel);

        disciplinesFrame.getContentPane().add(new JScrollPane(tableDisciplines));
        disciplinesFrame.getContentPane().add(addDisciplinePanel, BorderLayout.SOUTH);

    }

    private void initComponents() {
        tableDisciplines = new JTable();

        addDisciplinePanel = new JPanel();

        butAddDiscipline = new JButton("Добавить");
        tfDisciplineTitle = new JTextField();
        tfSemestr = new JTextField();
    }

    public JDialog getDisciplinesFrame() {
        return disciplinesFrame;
    }

    public JTable getTableDisciplines() {
        return tableDisciplines;
    }

    public JButton getButAddDiscipline() {
        return butAddDiscipline;
    }

    public JTextField getTfDisciplineTitle() {
        return tfDisciplineTitle;
    }

    public JTextField getTfSemestr() {
        return tfSemestr;
    }
}
