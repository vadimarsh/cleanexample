package domain.usecase.discipline;

import domain.entity.Discipline;
import domain.port.DisciplineRepository;

import java.util.List;

public class GetUnclosedDisciplinesUseCase {
    public GetUnclosedDisciplinesUseCase(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    private final DisciplineRepository disciplineRepository;

    public List<Discipline> invoke(){
        return this.disciplineRepository.getUnclosedDisciplines();
    }
}
