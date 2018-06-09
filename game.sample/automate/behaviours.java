package automate;

import onscreen.*;

public class behaviours {
	State src;
	Transition[] t;

	public behaviours(State e, Transition[] t) {
		this.t = t;
		src = e;
	}

	public void eval(Entity e) {
		int i = 0;
		if (src != e.courant) {
			return;
		} else {
			Transition[] t_ok = new Transition[t.length];
			int nb_trans = 0;
			while (i < t.length) {
				if (t[i].eval(e)) {
					t_ok[nb_trans] = t[i];
					nb_trans++;
				}
				i++;
			}
			if (nb_trans == 0) {
				t_ok[nb_trans++] = t[t.length - 1];
			}
			int rand = (int) (Math.random() * nb_trans);
			if (t_ok[rand].act instanceof Explode) {
				if (e instanceof Bullet)
					t_ok[rand].act.execute(((Bullet) e).comport.model, e);
				else
					t_ok[rand].act.execute(((Sbire) e).comport.model, e);
				e.courant = t_ok[rand].dest;
			} else {
				t_ok[rand].act.execute(e);
				e.courant = t_ok[rand].dest;
			}
		}
	}
}
