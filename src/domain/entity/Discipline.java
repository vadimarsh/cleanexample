package domain.entity;

public class Discipline {

    private int id;
    private String name;
    private boolean closed;
    private int semestr;

    @Override
    public String toString() {
        return "Discipline{" +
                "hash=" + super.toString() +
                "id=" + id +
                ", name='" + name + '\'' +
                ", closed=" + closed +
                ", semestr=" + semestr +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Discipline){
            if (this.id==((Discipline)obj).id){
                return true;
            }
        }
        return super.equals(obj);
    }

    public Discipline(int id, String name, boolean closed, int semestr) {

        this.id = id;
        this.name = name;
        this.closed = closed;
        this.semestr = semestr;
    }
    public int getId() {
        return id;
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
