package domain;

import java.util.ArrayList;

public class NormalClass extends ObjectClass {

    public NormalClass(Point positionPoint, Size size, String text) throws Exception {
        super(positionPoint, size, text);
    }

    @Override
    public ArrayList<Line> drawLines() throws Exception {
        ArrayList<Line> lines = super.drawLines();
        return lines;
    }

    @Override
    public ArrayList<Text> drawText()  throws Exception {
        ArrayList<Text> texts = super.drawText();
        return texts;
    }


    public int getLengthX(int percentage) {
        Size size = getSize();
        double length = size.getWidth() * (percentage/(double)100);
        return (int)length;
    }

    public int getLengthY(int percentage) {
        Size size = getSize();
        double length = size.getHeight() * (percentage/(double)100);
        return (int)length;
    }
}
