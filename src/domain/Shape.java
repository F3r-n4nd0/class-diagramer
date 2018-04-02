package domain;

import java.util.ArrayList;

public interface Shape {

    ArrayList<Line> drawLines() throws Exception;
    ArrayList<Text> drawText() throws Exception;

}
