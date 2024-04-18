package data.repository.inmemory;

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

    @Override
    public List<Discipline> getUnclosedDisciplines() {
        List<Discipline> unclosedDisciplines = new ArrayList<>();
        for (int i = 0; i < disciplines.size(); i++) {
            if(!disciplines.get(i).isClosed()){
                unclosedDisciplines.add(disciplines.get(i));
            }
        }
        return unclosedDisciplines;
    }
}
