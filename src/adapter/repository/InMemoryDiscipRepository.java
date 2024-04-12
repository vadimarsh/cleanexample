package adapter.repository;

import domain.entity.Discipline;
import domain.port.DisciplineRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDiscipRepository implements DisciplineRepository {
    private final List<Discipline> disciplines = new ArrayList<>();
    @Override
    public Discipline create(Discipline discipline) {
        this.disciplines.add(discipline);
        return discipline;
    }

    @Override
    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    @Override
    public void update(Discipline discipline) {
        disciplines.set(disciplines.indexOf(discipline),discipline);
    }
}
