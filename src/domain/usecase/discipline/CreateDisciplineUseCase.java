package domain.usecase.discipline;

import domain.entity.Discipline;
import domain.port.DisciplineRepository;

public class CreateDisciplineUseCase {
    private final DisciplineRepository disciplineRepository;

    public CreateDisciplineUseCase(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public Discipline invoke(Discipline discipline){
        return this.disciplineRepository.create(discipline);
    }
}
