package presentation.gui.discwindow;

import domain.entity.Discipline;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DisciplinesTableModel extends AbstractTableModel {
    private List<Discipline> disciplines;
    private final DisciplinesWindowController controller;


    public DisciplinesTableModel(DisciplinesWindowController controller, List<Discipline> disciplineList) {
        this.controller = controller;
        this.disciplines = disciplineList;
    }

    @Override
    public int getRowCount() {
        return disciplines.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Предмет";
            case 1: return "Семестр";
            case 2: return "Предмет сдан";
        }
        return super.getColumnName(column);
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Discipline discipline = disciplines.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return discipline.getName();
            }
            case 1: {
                return discipline.getSemestr();
            }
            case 2: {
                return discipline.isClosed();
            }
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: {
                return String.class;
            }
            case 1: {
                return String.class;
            }
            case 2: {
                return Boolean.class;
            }
        }
        return super.getColumnClass(columnIndex);
    }

}
