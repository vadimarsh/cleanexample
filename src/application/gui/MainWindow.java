package application.gui;

import application.config.GUISwingConfig;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    JTable jTable;
    JPanel controlPanel;
    EditPanel editPanel;
    GUISwingConfig defaultConfig;

    MainTableModel tableModel;

    public MainWindow(GUISwingConfig defaultConfig){
        super("Clean example");
        this.defaultConfig = defaultConfig;
        this.defaultConfig.getAllDiscliplines();

        initComponents();

        add(new JScrollPane(jTable));
        add(editPanel, BorderLayout.SOUTH);
        setSize(400,200);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private void initComponents(){
        jTable = new JTable();
        tableModel = new MainTableModel(defaultConfig);
        jTable.setModel(tableModel);
        controlPanel = new JPanel();
        editPanel = new EditPanel(this);
    }

}
