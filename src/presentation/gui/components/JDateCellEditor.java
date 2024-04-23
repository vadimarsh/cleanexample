package presentation.gui.components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class JDateCellEditor extends AbstractCellEditor
        implements TableCellEditor {
    private final JSpinner editor;
    public JDateCellEditor(){
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        model.setCalendarField(Calendar.DAY_OF_WEEK);
        editor = new JSpinner(model);
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        editor.setValue(value);
        return editor;
    }

    @Override
    public Object getCellEditorValue() {
        return editor.getValue();
    }
}
