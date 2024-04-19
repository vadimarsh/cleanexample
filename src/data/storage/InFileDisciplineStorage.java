package data.storage;

import domain.entity.Discipline;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InFileDisciplineStorage {
    private static final String DISCIPLINES_FNAME = "disciplines.ps";
    private BufferedReader reader;
    private BufferedWriter writer;
    public List<Discipline> readAll(){
        List<Discipline> readedDisciplines = new ArrayList<>();
        Discipline discipline = null;
        try {
            reader = new BufferedReader(new FileReader(DISCIPLINES_FNAME));
            while (reader.ready()){
                int id =Integer.parseInt(reader.readLine());
                String readedline =reader.readLine();
                String[] splitedLine = readedline.split("|");
                int disciplineID = Integer.parseInt(splitedLine[0]);
                String disciplineTitle = splitedLine[1];
                int disciplineSemestr = Integer.parseInt(splitedLine[2]);
                String disciplineStatusRaw = splitedLine[3];
                boolean disciplineStatus = false;
                if(disciplineStatusRaw.equals("Закрыто")){
                    disciplineStatus = true;
                }
                discipline = new Discipline(disciplineID, disciplineTitle,disciplineStatus,disciplineSemestr);
                readedDisciplines.add(discipline);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readedDisciplines;
    }
    public void saveAll(List<Discipline> disciplines) {
        try {
            writer = new BufferedWriter(new FileWriter(DISCIPLINES_FNAME,false));
            for (Discipline discipline : disciplines) {
                {
                    int disciplineID = discipline.getId();
                    String disciplineTitle = discipline.getName();
                    int disciplineSemestr = discipline.getSemestr();
                    String disciplineStatusRaw = "Долг";
                    boolean disciplineStatus = discipline.isClosed();
                    if (discipline.isClosed())
                    {
                        disciplineStatusRaw = "Закрыто";
                    }
                    writer.write(disciplineID+"|"+disciplineTitle+"|"+disciplineSemestr+"|"+disciplineStatusRaw);
                }
            }
            writer.close();
            } catch(IOException e) {

            throw new RuntimeException(e);
        }
    }

    public void save(Discipline discipline) {
        int disciplineID = discipline.getId();
        String disciplineTitle = discipline.getName();
        int disciplineSemestr = discipline.getSemestr();
        String disciplineStatusRaw = "Долг";
        boolean disciplineStatus = discipline.isClosed();
        if (discipline.isClosed())
        {
            disciplineStatusRaw = "Закрыто";
        }
        try {
            writer = new BufferedWriter(new FileWriter(DISCIPLINES_FNAME,true));
            writer.write(disciplineID + "|" + disciplineTitle + "|" + disciplineSemestr + "|" + disciplineStatusRaw);
            writer.close();
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
