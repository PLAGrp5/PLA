package onscreen;

import ui.*;

/**
 * Explode
 */
public class Explode extends Action {

    public Explode() {
    }

    public void execute(Model model, Entity e) {
        Point pe = new Point(e.p);
        Point p = pe.nextPoint(e.dir);
        Entity e1 = model.m.map[p.i][p.j];
        e1.updatevie(-1);
        model.del(e);
        if (e1.type != 'W' && e1.vie < 1)
            model.del(e1);
    }
}