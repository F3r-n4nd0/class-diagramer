package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObjectClassTest {


    @Test
    void createdClass() {

        Point positionPoint = new Point(100, 100);
        Size size = new Size(100, 100);
        String text = "Text Sample";


        try {
            new NormalClass(null, size, text);
            fail("Start point can't be null");
        } catch (Exception e) {
        }


        try {
            new NormalClass(positionPoint, null, text);
            fail("Size can't be null");
        } catch (Exception e) {
        }


        try {
            new NormalClass(positionPoint, size, null);
            fail("Text can't be empty");
        } catch (Exception e) {
        }


        try {
            new NormalClass(positionPoint, size, text);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


    @Test
    void validaClassDrawing() {

        Point startPoint = new Point(0, 0);
        Size size = new Size(100, 100);
        String text = "Text Sample";

        NormalClass normalClass = null;
        try {
            normalClass = new NormalClass(startPoint, size, text);
            ArrayList<Line> lines = normalClass.drawLines();
            ArrayList<Text> texts = normalClass.drawText();

            assertEquals(4, lines.size(), "Normal Class has 4 line");
            assertEquals(1, texts.size(), "Normal Class has 1 text");

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}