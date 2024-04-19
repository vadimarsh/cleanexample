package domain.usecase.discipline;

import domain.entity.Discipline;
import domain.port.DisciplineRepository;

import java.util.List;

public class GetAllDisciplinesUseCase {
    public GetAllDisciplinesUseCase(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    private final DisciplineRepository disciplineRepository;

    public List<Discipline> invoke(){
        return this.disciplineRepository.getAllDisciplines();
    }
}
