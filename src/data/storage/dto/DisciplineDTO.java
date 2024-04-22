package data.storage.dto;

import domain.entity.Discipline;

public class DisciplineDTO {
    private int id;
    private String name;
    private String semestr;
    private String closed;
    public DisciplineDTO(int id, String name, String semestr, String closed) {
        this.id = id;
        this.name = name;
        this.semestr = semestr;
        this.closed = closed;
    }

    public DisciplineDTO(Discipline discipline) {
        this.id = discipline.getId();
        this.name = discipline.getName();
        this.semestr = Integer.toString(discipline.getSemestr());
        String disciplineStatusRaw = "Долг";
        if (discipline.isClosed())
        {
            disciplineStatusRaw = "Закрыто";
        }
        this.closed = disciplineStatusRaw;
    }

    public Discipline toDiscipline(){
        boolean disciplineStatus = false;
        if(this.closed.equals("Закрыто")){
            disciplineStatus = true;
        }
        int semestr = Integer.parseInt(this.semestr);
        Discipline discipline = new Discipline(this.id,this.name,disciplineStatus,semestr);
        return discipline;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemestr() {
        return semestr;
    }

    public void setSemestr(String semestr) {
        this.semestr = semestr;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }
}
