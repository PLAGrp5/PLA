package onscreen;

/**
 * Bullet
 */
public class Bullet extends Entity {

    public Bullet(Entity e) {
        super('B', e.p.i, e.p.j, e.dir);
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
        aut = true;
        e.m_map.insert(this);
    }

    public Bullet(Entity e, Automate a, State s) {
        super('B', e.p.i, e.p.j, e.dir);
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
        aut = true;
        courant = s;
        comport = a;
        e.m_map.insert(this);
    }
}
