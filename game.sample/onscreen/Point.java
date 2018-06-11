package onscreen;

/* Classe Point */

public class Point {
	public int i, j; // abscisse et ordonnée

	// Initialisation d'un point sans paramètre
	public Point() {
		this.i = 0;
		this.j = 0;
	}

	// Initialisation d'un point avec les paramètres (x,y)
	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public Point(Point p) {
		i = p.i;
		j = p.j;
	}

    public Point nextPoint(char dir) {
        Point p1 = new Point(i, j);
        switch (dir) {
        case 'S':
            p1.i++;
            break;
        case 'W':
            p1.j--;
            break;
        case 'E':
            p1.j++;
            break;
        default:
            p1.i--;
            break;
        }
        return p1;
    }
}