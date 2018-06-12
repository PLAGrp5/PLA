package automate;

import onscreen.Bullet;
import onscreen.Entity;
import onscreen.Point;

public class Hit extends Action {

	public Hit() {
	}

	public void execute(Entity e) {

		// Blesse l'entité en face directement
		if (!e.canihit()) {
			Point p = new Point(e.p).nextPoint(e.dir);
			e.m_model.GetEntity(p).updatevie(e.m_model, -1);
			return;
		}
		Bullet b = new Bullet(e.m_model, e.m_model.m_bullet, 1L, e, e.m_model.automates[2], e.m_model.automates[2].init);
		e.m_model.add(b);
	}
}