package domain.port;

import domain.entity.Discipline;

import java.util.List;

public interface DisciplineRepository {
    Discipline create (Discipline discipline);

    List<Discipline> getAll();

    void update(Discipline discipline);

    Discipline getByID(int id);

}
