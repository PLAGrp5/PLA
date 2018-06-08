package onscreen;

/* Classe Point */

public class Point {
    public int i, j; // abscisse et ordonnée

    // Initialisation d'un point sans paramètre
    Point() {
        this.i = 0;
        this.j = 0;
    }

    // Initialisation d'un point avec les paramètres (x,y)
    Point(int i, int j) {
        this.i = i;
        this.j = j;
    }

    Point(Point p) {
        i = p.i;
        j = p.j;
    }

    Point nextPoint(char dir) {
        Point p1 = new Point(i, j);
        switch (dir) {
        case 'D':
            p1.i++;
            break;
        case 'L':
            p1.j--;
            break;
        case 'R':
            p1.j++;
            break;
        default:
            p1.i--;
            break;
        }
        return p1;
    }

    // modification d'un point par (newx,newy)
    void translate(int newi, int newj) {
        this.i += newi;
        this.j += newj;
    }
}