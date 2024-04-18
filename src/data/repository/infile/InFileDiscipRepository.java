package data.repository.infile;

import domain.entity.Discipline;
import domain.port.DisciplineRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InFileDiscipRepository implements DisciplineRepository {
    private final String disciplinesFName = "disciplines.ps";
    private Reader reader;
    private Writer writer;

    public InFileDiscipRepository(){
        try {
            reader = new BufferedReader(new FileReader(disciplinesFName));
            writer = new BufferedWriter(new FileWriter(disciplinesFName,false));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
