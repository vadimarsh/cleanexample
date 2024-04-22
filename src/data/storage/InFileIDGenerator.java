package data.storage;

import java.io.*;

public class InFileIDGenerator {
    private final String fname;
    private DataOutputStream writer;
    private DataInputStream reader;
    private int id;
    public InFileIDGenerator(String fname){
        try {
            this.fname = fname;
            if(!new File(fname).isFile()){
                System.out.println("no file");
                writer = new DataOutputStream(new FileOutputStream(this.fname,false));
                writer.writeInt(0);
                writer.close();
            }
            reader = new DataInputStream(new FileInputStream(this.fname));
            id=reader.readInt();
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int generate() {
        try {
            System.out.println("id "+fname+" id="+id);
            writer = new DataOutputStream(new FileOutputStream(fname,false));
            writer.writeInt(id);
            id++;
            writer.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
