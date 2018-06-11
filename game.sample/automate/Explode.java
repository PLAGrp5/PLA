package automate;

import java.awt.Color;

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
        Entity e1 = e.m_model.m_Map.map[p.i][p.j];
        e1.updatevie(e.m_model, -1);
        if (e1.vie <= 0) {
        	e1.alive = false;
        	e1.jauge_couleur = 0;
        }
        e.m_model.del(e);
    }
}