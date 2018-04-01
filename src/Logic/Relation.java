package Logic;

/**
 * Created by DEVELOPER-PC on 31/03/2018.
 */
public class Relation {
    private final MainClass entityClassOno;
    private final MainClass entityClassTwo;
    private final Conector conector;

    public Relation(MainClass entityClassOno, MainClass entityClassTwo, Conector conector) {
        this.entityClassOno = entityClassOno;
        this.entityClassTwo = entityClassTwo;
        this.conector = conector;
    }
}