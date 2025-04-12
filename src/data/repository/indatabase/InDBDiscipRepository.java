package data.repository.indatabase;

import data.repository.Storage;
import data.storage.SQLiteStorage;
import data.storage.dto.DisciplineDTO;
import domain.entity.Discipline;
import domain.port.DisciplineRepository;

import java.util.ArrayList;
import java.util.List;

public class InDBDiscipRepository implements DisciplineRepository {
    private Storage storage;
    private List<Discipline> disciplines = new ArrayList<>();

    public InDBDiscipRepository(Storage storage){
        this.storage=storage;
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
        storage.updateDiscipline(new DisciplineDTO(discipline));
    }

    @Override
    public Discipline getByID(int id) {
        Discipline result = null;
        for (Discipline discipline: disciplines
             ) {            if(discipline.getId()==id){
                 result = discipline;
                 break;
                }
        }
        return result;
    }
}
