package Domain.Shape;

import Domain.Shape.Models.Line;
import Domain.Shape.Models.Polygon;
import Domain.Shape.Models.Text;

import java.util.ArrayList;
import java.util.List;

public class ObjectsToDraw {

    private List<Line> lines;
    private List<Polygon> polygons;
    private List<Text> texts;

    public ObjectsToDraw(List<Line> lines, List<Polygon> polygons, List<Text> texts) {
        this.lines = lines;
        this.polygons = polygons;
        this.texts = texts;
    }

    public List<Line> getLines() {
        if (lines == null) {
            return new ArrayList<Line>();
        }
        return lines;
    }

    public List<Polygon> getPolygons() {
        if (polygons == null) {
            return new ArrayList<Polygon>();
        }
        return polygons;
    }

    public List<Text> getTexts() {
        if (texts == null) {
            return new ArrayList<Text>();
        }
        return texts;
    }
}
