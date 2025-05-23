package data.repository.infile;

import data.repository.Storage;
import data.storage.dto.DisciplineDTO;
import domain.entity.Discipline;
import domain.port.DisciplineRepository;

import java.util.ArrayList;
import java.util.List;

public class InFileDiscipRepository implements DisciplineRepository {
   private Storage storage;
   private List<Discipline> disciplines = new ArrayList<>();

    public InFileDiscipRepository(Storage storage){
       this.storage=storage;
       disciplines= getAll();
    }

    @Override
    public Discipline create(Discipline discipline) {
        DisciplineDTO disciplineDTO = new DisciplineDTO(discipline);
        Discipline newDiscipline = (this.storage.addDiscipline(disciplineDTO)).toDiscipline();
        this.disciplines.add(newDiscipline);
        return newDiscipline;
    }

    @Override
    public List<Discipline> getAll() {
        List<DisciplineDTO> disciplineDTOS = storage.readAllDisciplines();
        disciplines = new ArrayList<>();
        for (int i = 0; i < disciplineDTOS.size(); i++) {
            disciplines.add(disciplineDTOS.get(i).toDiscipline());

        }
        return disciplines;
    }

    @Override
    public void update(Discipline discipline) {
        disciplines.set(disciplines.indexOf(discipline),discipline);
        storage.updateDiscipline(new DisciplineDTO(discipline));
    }

    @Override
    public Discipline getByID(int id) {
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
