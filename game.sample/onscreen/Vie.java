package onscreen;

public class Vie extends BonusEtMalusFixes{
	int PV;
	public Vie(int pv) {
		this.PV = pv;
	}
	public void use(Entity ent) {
		if(ent.vie + 5 >= ent.vie_max)
			ent.vie = ent.vie_max;
		else
			ent.vie += PV;
		ent.inventaire[0] = ent.inventaire[1];
		ent.inventaire[1] = ent.inventaire[2];
		ent.inventaire[2] = null;
	}
}
