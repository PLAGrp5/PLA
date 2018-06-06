package onscreen;

/**
 * Bullet
 */
public class Bullet extends Entity {

    public Bullet(Entity e) {
        super('B', e.p.i, e.p.j, e.dir);
        color = e.color;
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
}
