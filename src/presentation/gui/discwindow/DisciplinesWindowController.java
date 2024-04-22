package presentation.gui.discwindow;

import domain.entity.Discipline;
import domain.usecase.discipline.CreateDisciplineUseCase;
import domain.usecase.discipline.GetAllDisciplinesUseCase;
import presentation.config.GUISwingConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisciplinesWindowController {
    private final DisciplinesWindowView view;
    private DisciplinesTableModel disciplinesTableModel;
    private final GUISwingConfig config;

    private GetAllDisciplinesUseCase getAllDisciplinesUseCase;
    private CreateDisciplineUseCase createDisciplineUseCase;

    public DisciplinesWindowController(GUISwingConfig config, DisciplinesWindowView disciplinesWindowView) {
        this.config = config;
        this.view =disciplinesWindowView;

        getAllDisciplinesUseCase = config.getAllDiscliplines();
        createDisciplineUseCase = config.createDiscipline();

        setTableModel();
        setControllers();
    }

    private void setControllers() {
        this.view.getButAddDiscipline().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Discipline discipline = new Discipline(-1,view.getTfDisciplineTitle().getText(),false, Integer.parseInt(view.getTfSemestr().getText()));
                createDisciplineUseCase.invoke(discipline);
                disciplinesTableModel.fireTableDataChanged();
            }
        });

    }

    private void setTableModel() {
        disciplinesTableModel = new DisciplinesTableModel(this,getAllDisciplinesUseCase.invoke());
        this.view.getTableDisciplines().setModel(disciplinesTableModel);
    }
}
