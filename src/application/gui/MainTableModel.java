package application.gui;

import application.config.GUISwingConfig;
import domain.entity.Task;
import domain.usecase.task.GetAllTasksUseCase;

import javax.swing.table.AbstractTableModel;

public class MainTableModel extends AbstractTableModel  {
    private GUISwingConfig guiSwingConfig;
    private GetAllTasksUseCase getAllTasksUseCase;
    public MainTableModel(GUISwingConfig guiSwingConfig){
        this.guiSwingConfig = guiSwingConfig;
        getAllTasksUseCase = this.guiSwingConfig.getAllTasks();
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
        switch (columnIndex){
            case 0:{return task.getTitle();}
            case 1:{return task.getDiscipline().getName();}
            case 2:{return task.isClosed();}
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:{return String.class;}
            case 1:{return String.class;}
            case 2:{return Boolean.class;}
        }
        return super.getColumnClass(columnIndex);
    }
}
