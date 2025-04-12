package domain.usecase.discipline;

import domain.entity.Discipline;
import domain.port.DisciplineRepository;

import java.util.ArrayList;
import java.util.List;

public class GetUnclosedDisciplinesUseCase {
    public GetUnclosedDisciplinesUseCase(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    private final DisciplineRepository disciplineRepository;

    public List<Discipline> invoke(){
        List<Discipline> disciplines = this.disciplineRepository.getAll();
        List<Discipline> unClosedDisciplines = new ArrayList<>();
        for (int i = 0; i < disciplines.size(); i++) {
            if(!disciplines.get(i).isClosed()){
                unClosedDisciplines.add(disciplines.get(i));
            }
        }
        return unClosedDisciplines;
    }
}
