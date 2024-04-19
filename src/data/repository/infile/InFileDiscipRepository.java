package data.repository.infile;

import data.storage.InFileDisciplineStorage;
import data.storage.InFileIDGenerator;
import domain.entity.Discipline;
import domain.port.DisciplineRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InFileDiscipRepository implements DisciplineRepository {
   private InFileDisciplineStorage storage;
   private InFileIDGenerator idGenerator;
   private List<Discipline> disciplines = new ArrayList<>();

    public InFileDiscipRepository(InFileDisciplineStorage storage, InFileIDGenerator idGenerator){
       this.storage=storage;
       this.idGenerator=idGenerator;
       disciplines=getAllDisciplines();
    }


    @Override
    public Discipline create(Discipline discipline) {
        Discipline newDiscipline = new Discipline(idGenerator.generate(),discipline.getName(), discipline.isClosed(), discipline.getSemestr());
        this.disciplines.add(discipline);
        this.storage.save(newDiscipline);
        return newDiscipline;
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        disciplines = storage.readAll();
        return disciplines;
    }

    @Override
    public void update(Discipline discipline) {
        disciplines.set(disciplines.indexOf(discipline),discipline);
        storage.saveAll(disciplines);
    }

    @Override
    public Discipline getDisciplineByID(int id) {
        Discipline discipline = null;
        for (int i = 0; i < disciplines.size(); i++) {
             discipline = disciplines.get(i);
            if(discipline.getId()==id){
                return disciplines.get(i);
            }

        }
        System.out.println(id);
        return null;
    }

}
