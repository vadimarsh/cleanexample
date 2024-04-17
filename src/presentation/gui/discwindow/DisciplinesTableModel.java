package presentation.gui.discwindow;

import domain.entity.Discipline;
import domain.entity.Task;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import domain.usecase.task.GetAllTasksUseCase;
import presentation.config.GUISwingConfig;

import javax.swing.table.AbstractTableModel;

public class DisciplinesTableModel extends AbstractTableModel {
    private GUISwingConfig guiSwingConfig;
    private GetAllDisciplinesUseCase getAllDisciplinesUseCase;

    public DisciplinesTableModel(GUISwingConfig guiSwingConfig) {
        this.guiSwingConfig = guiSwingConfig;
        getAllDisciplinesUseCase = this.guiSwingConfig.getAllDiscliplines();
    }

    @Override
    public int getRowCount() {
        return getAllDisciplinesUseCase.invoke().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Discipline discipline = getAllDisciplinesUseCase.invoke().get(rowIndex);
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
