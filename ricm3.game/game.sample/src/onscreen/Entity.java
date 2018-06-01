package onscreen;

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

    public void turn(char dir) {
        this.dir = dir;
    }

    public void opposite() {
        switch (this.dir) {
        case 'D':
            this.dir = 'U';
            break;
        case 'L':
            this.dir = 'R';
            break;
        case 'R':
            this.dir = 'L';
            break;
        default:
            this.dir = 'D';
            break;
        }
    }
}