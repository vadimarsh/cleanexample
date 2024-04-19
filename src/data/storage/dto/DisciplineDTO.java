package data.storage.dto;

public class DisciplineDTO {
    private int id;

    public DisciplineDTO(int id, String name, String semestr, String closed) {
        this.id = id;
        this.name = name;
        this.semestr = semestr;
        this.closed = closed;
    }

    private String name;
    private String semestr;
    private String closed;
}
