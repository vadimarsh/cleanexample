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

        BoxLayout boxLayout = new BoxLayout(addDisciplinePanel,BoxLayout.Y_AXIS);
        addDisciplinePanel.setLayout(boxLayout);
        addDisciplinePanel.add(tfDisciplineTitle);
        addDisciplinePanel.add(tfSemestr);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(butAddDiscipline);
        addDisciplinePanel.add(panel);

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
