package presentation.gui.mainwindow;

import domain.entity.Task;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class MainTableModel extends AbstractTableModel {
    private final MainWindowController controller;

    private List<Task> tasks;
    public MainTableModel(MainWindowController controller, List<Task> tasks) {
        this.controller = controller;
        this.tasks = tasks;
        }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return task.getTitle();
            }
            case 1: {
                return task.getDiscipline().getName();
            }
            case 2: {
                return new Date(task.getDeadline());
            }

            case 3: {
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
                return Date.class;
            }
            case 3: {
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
        if (columnIndex == 3) {
            return true;
        }
        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Задача";
            case 1: return "Предмет";
            case 2: return "Дедлайн";
            case 3: return "Завершена";
        }
        return super.getColumnName(column);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            Task task = tasks.get(rowIndex);
            if (!task.isClosed()) {
                Date date = (Date)aValue;
                controller.updateDeadlineTask(task, date.getTime());
            }

        }
        if (columnIndex == 3) {
            Task task = tasks.get(rowIndex);
            if (!task.isClosed()) {
                controller.closeTask(task);
            }
        }
    }

    public Task getSelectedTask(int selectedRow) {
        Task selectedTask;
        selectedTask = tasks.get(selectedRow);
        System.out.println("selectedRow = "+selectedRow);
        System.out.println("selectedTaskIndex = "+tasks.indexOf(selectedTask));
        System.out.println("task = " + selectedTask);
        return selectedTask;
    }

}
