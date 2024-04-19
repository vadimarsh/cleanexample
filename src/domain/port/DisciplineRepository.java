package domain.port;

import domain.entity.Discipline;

import java.util.List;

public interface DisciplineRepository {
    Discipline create (Discipline discipline);

    List<Discipline> getAllDisciplines();

    void update(Discipline discipline);

    Discipline getDisciplineByID(int id);

}
