package domain.port;

import domain.entity.Discipline;

import java.util.List;

public interface DisciplineRepository {
    Discipline create (Discipline discipline);

    List<Discipline> getDisciplines();

    void update(Discipline discipline);

    List<Discipline> getUnclosedDisciplines();
}
