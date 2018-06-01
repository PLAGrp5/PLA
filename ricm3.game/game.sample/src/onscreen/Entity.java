package onscreen;

/**
 * Entity
 */
public class Entity {

    Point p;
    char dir;
    char type;

    Entity(char type, int i, int j, char dir) {
        this.type = type;
        this.p = new Point(i, j);
        this.dir = dir;
    }

    Entity(char type, int i, int j) {
        this.type = type;
        this.p = new Point(i, j);
    }

    void turn(char dir) {
        this.dir = dir;
    }

    boolean canimove(Map m, int i, int j) {
        return m.isfree(i, j) || m.isfree(i, j);
    }

    Point nextstep() {
        Point p = new Point(this.p.i, this.p.j);
        switch (this.dir) {
        case 'D':
            p.i++;
            break;
        case 'L':
            p.j--;
            break;
        case 'R':
            p.j++;
            break;
        default:
            p.i--;
            break;
        }
        return p;
    }

    void move(Map m, char dir) {
        if (this.dir != dir)
            this.turn(dir);
        else {
            Point p = nextstep(); // calcul nouvel coordonnées
            if (canimove(m, p.i, p.j)) {
                m.free(this.p.i, this.p.j);
                this.p = p;
                m.insert(this);
            }
        }
    }
}