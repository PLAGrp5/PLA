package automate;

import onscreen.*;

public class behaviours {
	State src;
	Transition[] t;

	public behaviours(State e, Transition[] t) {
		this.t = t;
		src = e;
	}

	public int eval(Entity e) {
		int i = 0;
		if (!src.compare(e.courant.nom)) {
			return 0;
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
			t_ok[rand].act.execute(e);
			if(!e.aut_bonus)
				e.courant = t_ok[rand].dest;
			return 1;
		}
	}
}
