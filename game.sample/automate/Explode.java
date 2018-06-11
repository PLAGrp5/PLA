package automate;

import onscreen.Entity;
import onscreen.Point;

/**
 * Explode
 */
public class Explode extends Action {

    public Explode() {
    }

    public void execute(Entity e) {
        Point pe = new Point(e.p);
        Point p = pe.nextPoint(e.dir);
        Entity e1 = e.m_model.m.map[p.i][p.j];
        e1.updatevie(e.m_model, -1);
        e.m_model.del(e);
    }
}