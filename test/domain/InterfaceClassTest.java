package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceClassTest {


    @Test
    void validaClassDrawing() {

        Point startPoint = new Point(0, 0);
        Size size = new Size(100, 100);
        String text = "Text Sample";

        InterfaceClass interfaceClass = null;
        try {
            interfaceClass = new InterfaceClass(startPoint, size, text);
            ArrayList<Line> lines = interfaceClass.drawLines();
            ArrayList<Text> texts = interfaceClass.drawText();

            assertEquals(8, lines.size(), "Interface Class has 8 line");
            assertEquals(2, texts.size(), "Interface Class has 2 text");

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}