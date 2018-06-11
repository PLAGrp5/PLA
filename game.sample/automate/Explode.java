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
        Point p = new Point(e.p).nextPoint(e.dir);
        Entity el = e.m_model.GetEntity(p);
        el.updatevie(e.m_model, -1);
        e.m_model.del(e);
    }
}