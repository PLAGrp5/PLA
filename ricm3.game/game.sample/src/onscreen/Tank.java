package onscreen;

/* Classe permettant de modéliser les tanks dans le jeu
 */
public class Tank extends Entity {

    public Tank(int i, int j, char dir) {
        super('T', i, j, dir);
    }

    public Point nextstep() {
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

    public boolean canimove(Map m, int i, int j) {
        return m.isfree(i, j) || m.isbonus(i, j);
    }

    public void move(Map m, char dir) {
        if (this.dir != dir)
            this.turn(dir);
        else {
            Point p = nextstep(); // calcul nouvel coordonnées
            if (canimove(m, p.i, p.j)) {
                m.free(this.p.i, this.p.j);
                this.p = p;
                m.insert(this);
            } else if (m.map[p.i][p.j].type == 'T')
                this.opposite();
        }
    }
}