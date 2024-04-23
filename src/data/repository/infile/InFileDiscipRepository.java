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
       disciplines=getAllDisciplines();
    }

    @Override
    public Discipline create(Discipline discipline) {
        DisciplineDTO disciplineDTO = new DisciplineDTO(discipline);
        Discipline newDiscipline = (this.storage.addDiscipline(disciplineDTO)).toDiscipline();
        this.disciplines.add(newDiscipline);
        return newDiscipline;
    }

    @Override
    public List<Discipline> getAllDisciplines() {
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
        List<DisciplineDTO> disciplinesDTO = new ArrayList<>();
        for (int i = 0; i < disciplines.size(); i++) {
            disciplinesDTO.add(new DisciplineDTO(disciplines.get(i)));
        }
        storage.saveAllDisciplines(disciplinesDTO);
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
