package presentation.gui.mainwindow;

import domain.entity.Discipline;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.EventListener;
import java.util.List;

public class DisciplComboBoxModel implements ComboBoxModel<String> {
    List<Discipline> disciplines;
    Discipline selectedDiscipline;
    protected EventListenerList listenerList = new EventListenerList();

    public DisciplComboBoxModel(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public void setSelectedItem(Object anObject) {
        if ((selectedDiscipline != null && !selectedDiscipline.getName().equals( anObject )) ||
                selectedDiscipline == null && anObject != null) {
            selectedDiscipline = findDisciplineByName(anObject.toString());
            System.out.println(selectedDiscipline);
            fireContentsChanged(this, -1, -1);
        }
    }

    private Discipline findDisciplineByName(String string) {
        for (int i = 0; i < disciplines.size(); i++) {
            if(disciplines.get(i).getName().equals(string)){
                return disciplines.get(i);
            }
        }
        return null;
    }

    @Override
    public String getSelectedItem() {
        if (selectedDiscipline!=null){
        return selectedDiscipline.getName();}
        else return "Выберите";
    }

    public Discipline getSelectedDiscipline() {
        return selectedDiscipline;
    }

    @Override
    public int getSize() {
        return disciplines.size();
    }


    @Override
    public String getElementAt(int index) {
        return disciplines.get(index).getName();
    }


    public void addListDataListener(ListDataListener l) {
        listenerList.add(ListDataListener.class, l);
    }

    public void removeListDataListener(ListDataListener l) {
        listenerList.remove(ListDataListener.class, l);
    }

    public ListDataListener[] getListDataListeners() {
        return listenerList.getListeners(ListDataListener.class);
    }

    protected void fireContentsChanged(Object source, int index0, int index1)
    {
        Object[] listeners = listenerList.getListenerList();
        ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDataListener.class) {
                if (e == null) {
                    e = new ListDataEvent(source, ListDataEvent.CONTENTS_CHANGED, index0, index1);
                }
                ((ListDataListener)listeners[i+1]).contentsChanged(e);
            }
        }
    }





}
