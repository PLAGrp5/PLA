package onscreen;

/*class tank*/

public class Tank {

    Point p = new Point();
    char front;

    Tank(int i, int j, char f) {
        this.p.i = i;
        this.p.j = j;
        this.front = f;
    }

    void turn(char dir) {
        this.front = dir;
    }

    boolean canimove(Map map, int i, int j) {
        return map.map[i][j] == 'f' || map.map[i][j] == 'b'; // A modifier avec par exmple la classe case
    }

    Point nextstep() {
        Point p = new Point(this.p.i, this.p.j);
        switch (this.front) {
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

    void move(Map map, char dir) {
        if (this.front != dir)
            this.turn(dir);
        else {
            Point p = nextstep(); // calcul nouvel coordonnées
            if (canimove(map, p.i, p.j)) {
                map.map[this.p.i][this.p.j] = 'f';
                this.p = p;
                map.map[p.i][p.j] = 'T';
            }
        }
    }
}