package onscreen;

/**
 * Bullet
 */
public class Bullet extends Entity {

		public Automate comport;
		
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
        courant = s;
        comport = a;
        e.m_map.insert(this);
    }
}
