package data.repository.inmemory;

import domain.entity.Discipline;
import domain.port.DisciplineRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InMemoryDiscipRepository implements DisciplineRepository {
    private final List<Discipline> disciplines = new ArrayList<>();
    @Override
    public Discipline create(Discipline discipline) {
        this.disciplines.add(discipline);
        return discipline;
    }

    @Override
    public List<Discipline> getAll() {
        return disciplines;
    }

    @Override
    public void update(Discipline discipline) {
        disciplines.set(disciplines.indexOf(discipline),discipline);
    }

    @Override
    public Discipline getByID(int id) {
        Discipline discipline = null;
        for (int i = 0; i < disciplines.size(); i++) {
            discipline = disciplines.get(i);
            if(discipline.getId()==id){

                return disciplines.get(i);
            }
        }throw new NoSuchElementException();

    }

}
