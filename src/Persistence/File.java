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
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder = gsonBuilder.setPrettyPrinting();
//            Gson gson = gsonBuilder.create();
//            String json = gson.toJson(shapes);
////            gson.toJson(shapes, new FileWriter("../file.json"));
//
//            BufferedWriter out = new BufferedWriter(new FileWriter("../file.json"));
//            out.write(json);
//            out.close();

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
//            FileReader fr = new FileReader("../file.json");
//            BufferedReader br = new BufferedReader(fr);
//            String sCurrentLine;
//            String text = "";
//            while ((sCurrentLine = br.readLine()) != null) {
//                text = sCurrentLine + sCurrentLine;
//            }
//
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder = gsonBuilder.setPrettyPrinting();
//            Gson gson = gsonBuilder.create();
//
//            List<Shape> shapes = gson.fromJson(new FileReader("../file.json"), new ArrayList<Shape>().getClass());

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
