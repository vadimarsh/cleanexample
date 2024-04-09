package application.gui;

import application.config.GUISwingConfig;

import javax.swing.*;
import java.awt.*;

public class MainWindowView {
    private JFrame mainFrame;
    private JTable jTable;
    private JPanel controlPanel;
    private JPanel editPanel;
    private JButton butConfirm;
    private JTextField tfTaskTitle;
    private JTextField tfSemestr;




    public MainWindowView(){
        mainFrame = new JFrame("Clean example");

        initComponents();
        placeComponents();

        mainFrame.setSize(400,200);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    private void initComponents(){
        jTable = new JTable();

        editPanel = new JPanel();


        butConfirm = new JButton("Добавить");
        tfTaskTitle = new JTextField();
        tfSemestr = new JTextField();
    }

    private void placeComponents() {
        BoxLayout boxLayout = new BoxLayout(editPanel,BoxLayout.Y_AXIS);
        editPanel.setLayout(boxLayout);
        editPanel.add(tfTaskTitle);
        editPanel.add(tfSemestr);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(butConfirm);
        editPanel.add(panel);

        mainFrame.add(new JScrollPane(jTable));
        mainFrame.add(editPanel, BorderLayout.SOUTH);
    }
    public JFrame getMainFrame() {
        return mainFrame;
    }
    public JTable getjTable() {
        return jTable;
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }

    public JPanel getEditPanel() {
        return editPanel;
    }

    public JButton getButConfirm() {
        return butConfirm;
    }

    public JTextField getTfTaskTitle() {
        return tfTaskTitle;
    }

    public JTextField getTfSemestr() {
        return tfSemestr;
    }
}
