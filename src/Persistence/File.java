package Persistence;

import Domain.Board.Repository;
import Domain.Shape.Shape;

import java.io.*;
import java.util.List;

public class File implements Repository {


    private String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(List<Shape> shapes) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(shapes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Shape> load() throws Exception {
        try {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            List<Shape> shapes = (List<Shape>) in.readObject();
            in.close();
            return shapes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
