package domain.entity;

public class Discipline {
    private String name;
    private boolean closed;
    private int semestr;

    public Discipline(String name, boolean closed, int semestr) {
        this.name = name;
        this.closed = closed;
        this.semestr = semestr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getSemestr() {
        return semestr;
    }

    public void setSemestr(int semestr) {
        this.semestr = semestr;
    }
}
