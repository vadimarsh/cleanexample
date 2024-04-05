package domain.entity;

import java.util.Date;

public class Task {
    private String title;
    private boolean closed;
    private Date deadline;
    private Discipline discipline;

    public Task(String title, boolean closed, Date deadline, Discipline discipline) {
        this.title = title;
        this.closed = closed;
        this.deadline = deadline;
        this.discipline = discipline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
