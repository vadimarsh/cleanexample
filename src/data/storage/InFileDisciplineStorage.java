package data.storage;

import data.storage.dto.DisciplineDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InFileDisciplineStorage {
    private static final String DISCIPLINES_FNAME = "disciplines.ps";
    private BufferedReader reader;
    private BufferedWriter writer;
    private InFileIDGenerator idGenerator;
    public InFileDisciplineStorage(InFileIDGenerator idGenerator){
           this.idGenerator = idGenerator;
           File file = new File(DISCIPLINES_FNAME);
           if(!file.isFile()){
               try {
                   file.createNewFile();
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           }
    }
    public List<DisciplineDTO> readAll(){
        List<DisciplineDTO> readedDisciplines = new ArrayList<>();
        DisciplineDTO disciplineDTO;
        try {
            reader = new BufferedReader(new FileReader(DISCIPLINES_FNAME));
            while (reader.ready()){
                String readedline =reader.readLine();
                String[] splitedLine = readedline.split("\\|");
                int disciplineID = Integer.parseInt(splitedLine[0]);
                String disciplineTitle = splitedLine[1];
                String disciplineSemestr = splitedLine[2];
                String disciplineStatusRaw = splitedLine[3];

                disciplineDTO = new DisciplineDTO(disciplineID,disciplineTitle,disciplineSemestr,disciplineStatusRaw);
               // discipline = new Discipline(disciplineID, disciplineTitle,disciplineStatus,disciplineSemestr);
                readedDisciplines.add(disciplineDTO);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readedDisciplines;
    }
    public void saveAll(List<DisciplineDTO> disciplines) {
        try {
            writer = new BufferedWriter(new FileWriter(DISCIPLINES_FNAME,false));
            for (DisciplineDTO discipline : disciplines) {
                {
                    writer.write(discipline.getId()+"|"+discipline.getName()+"|"+discipline.getSemestr()+"|"+discipline.getClosed());
                    writer.newLine();
                }
            }
            writer.close();
            } catch(IOException e) {

            throw new RuntimeException(e);
        }
    }

    public DisciplineDTO save(DisciplineDTO discipline) {
        discipline.setId(idGenerator.generate());
        System.out.println("new id = "+discipline.getId());
        try {
            writer = new BufferedWriter(new FileWriter(DISCIPLINES_FNAME,true));
            writer.write(discipline.getId() + "|" + discipline.getName() + "|" + discipline.getSemestr() + "|" + discipline.getClosed());
            writer.newLine();
            writer.close();
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
        return discipline;
    }
}
