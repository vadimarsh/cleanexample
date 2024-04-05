package usecase.port;

import domain.entity.Discipline;

public interface DisciplineRepository {
    Discipline create (Discipline discipline);
}
