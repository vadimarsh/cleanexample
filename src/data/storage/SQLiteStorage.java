package data.storage;

import data.repository.Storage;
import data.storage.dto.DisciplineDTO;
import data.storage.dto.TaskDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteStorage implements Storage {
    public static final String url = "jdbc:sqlite:mydb.db";
    private static SQLiteStorage instance = null;
    private Connection connection;

    public static SQLiteStorage getInstance(){
        if(instance == null){
            instance = new SQLiteStorage();
        }
        return instance;
    }
    private SQLiteStorage(){

        initDB();
        createDB();
    }

    public void createDB(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE if not exists 'disciplines' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' TEXT, 'semestr' TEXT, 'closed' TEXT);";
            statement.execute(sql);
            sql = "CREATE TABLE if not exists 'tasks' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' TEXT, 'deadline' TEXT, 'closed' text, 'discipline_id' INTEGER NOT NULL, FOREIGN KEY (discipline_id) REFERENCES disciplines(id));";
            statement.execute(sql);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void initDB(){
        try {
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("The driver name is " + metaData.getDriverName());
                System.out.println("A new database has been created.");

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<TaskDTO> readAllTasks() {
        ArrayList<TaskDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM 'tasks';";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String closed = resultSet.getString("closed");
                String deadline = resultSet.getString("deadline");
                int disciplineId = resultSet.getInt("discipline_id");
                result.add(new TaskDTO(id,name,deadline,closed,disciplineId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TaskDTO addTask(TaskDTO task) {
        String sql = "INSERT INTO tasks('name','deadline','closed',discipline_id) " + "VALUES(?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,task.getName());
            preparedStatement.setObject(2,task.getDeadline());
            preparedStatement.setObject(3,task.getStatus());
            preparedStatement.setObject(4,task.getDiscipline_id());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                task.setId(id);
            }
            System.out.println("Задача сохранена в БД, id="+task.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка сохранения задачи");
        }
        return task;
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) {

        String sql = "UPDATE tasks SET closed = ? WHERE id = ?;";
        try {
            System.out.println("disc= "+task.getId());
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,task.getStatus());
            preparedStatement.setString(2,""+task.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public List<DisciplineDTO> readAllDisciplines() {
        ArrayList<DisciplineDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM 'disciplines';";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){

                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String closed = resultSet.getString("closed");
                String semestr = resultSet.getString("semestr");
                result.add(new DisciplineDTO(id,name,semestr,closed));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public DisciplineDTO updateDiscipline(DisciplineDTO discipline) {
        String sql = "UPDATE disciplines SET closed = ? WHERE id = ?;";
        try {
            System.out.println("disc= "+discipline.getId());
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,discipline.getClosed());
            preparedStatement.setString(2,""+discipline.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discipline;
    }

    @Override
    public DisciplineDTO addDiscipline(DisciplineDTO discipline) {
        String sql = "INSERT INTO disciplines('name','semestr','closed') " + "VALUES(?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,discipline.getName());
            preparedStatement.setObject(2,discipline.getSemestr());
            preparedStatement.setObject(3,discipline.getClosed());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                discipline.setId(id);
            }

            System.out.println(discipline.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discipline;
    }

    @Override
    public boolean deleteTask(TaskDTO task) {
        boolean flag = false;
        String sql = "DELETE FROM tasks WHERE id = ?;";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,task.getId());
            if(preparedStatement.executeUpdate() >0 ){
             flag = true;
             System.out.println("disc= "+task.getId());
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void closeDB(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
