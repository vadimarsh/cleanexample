package domain.entity;

public class Task {
    private int id;
    private String title;
    private boolean closed;
    private long deadline;
    private Discipline discipline;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", closed=" + closed +
                ", deadline=" + deadline +
                ", discipline=" + discipline +
                '}';
    }

    public Task(int id, String title, boolean closed, long deadline, Discipline discipline) {
        this.id=id;
        this.title = title;
        this.closed = closed;
        this.deadline = deadline;
        this.discipline = discipline;
    }
    public int getId() {
        return id;
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

    public void changeState() {
        this.closed = !this.closed;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
