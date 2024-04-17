package presentation.gui.mainwindow;

import domain.entity.Discipline;
import domain.usecase.discipline.CloseDisciplineUseCase;
import presentation.config.GUISwingConfig;
import domain.entity.Task;
import domain.usecase.task.GetAllTasksUseCase;
import domain.usecase.task.MarkDoneTaskUseCase;

import javax.swing.table.AbstractTableModel;

public class MainTableModel extends AbstractTableModel {
    private GUISwingConfig guiSwingConfig;
    private GetAllTasksUseCase getAllTasksUseCase;
    private MarkDoneTaskUseCase markDoneTaskUseCase;
    private CloseDisciplineUseCase closeDisciplineUseCase;

    public MainTableModel(GUISwingConfig guiSwingConfig) {
        this.guiSwingConfig = guiSwingConfig;
        getAllTasksUseCase = this.guiSwingConfig.getAllTasks();
        markDoneTaskUseCase = this.guiSwingConfig.markDoneTask();
        closeDisciplineUseCase = this.guiSwingConfig.closeDiscipline();
    }

    @Override
    public int getRowCount() {
        return getAllTasksUseCase.invoke().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = getAllTasksUseCase.invoke().get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return task.getTitle();
            }
            case 1: {
                return task.getDiscipline().getName();
            }
            case 2: {
                return task.isClosed();
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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            Task task = getAllTasksUseCase.invoke().get(rowIndex);
            if (!task.isClosed()) {
                task.setClosed((Boolean) aValue);
                markDoneTaskUseCase.invoke(task);
                Discipline discipline = task.getDiscipline();
                closeDisciplineUseCase.invoke(discipline);
            }

        }

    }
}
