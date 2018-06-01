package src.onscreen;

/* Classe Point */

public class Point {
    int i, j; // abscisse et ordonnée

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

    // modification d'un point par (newx,newy)
    void translate(int newi, int newj) {
        this.i += newi;
        this.j += newj;
    }
}