package src.onscreen;

/*
Classe permettant de modélier toutes les identités du jeu
 */
public class Entity {

    Point p;
    char dir;
    char type;

    public Entity(char type, int i, int j, char dir) {
        this.type = type;
        this.p = new Point(i, j);
        this.dir = dir;
    }

    public Entity(char type, int i, int j) {
        this.type = type;
        this.p = new Point(i, j);
    }

    void turn(char dir) {
        this.dir = dir;
    }
}