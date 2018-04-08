package Persistence;

import Domain.Board.Repository;
import Domain.Shape.Shape;

import javax.swing.*;
import java.io.*;
import java.util.List;

public class File implements Repository {

    protected JFileChooser chooser = new JFileChooser(".");
    private String fileName;

    public File() {

    }

    @Override
    public void save(List<Shape> shapes) throws Exception {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("../file.data"));
            out.writeObject(shapes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Shape> load() throws Exception {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("../file.data"));
            List<Shape> shapes = (List<Shape>) in.readObject();
            in.close();
            return shapes;

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }


}
