package onscreen;

public class Vie extends BonusEtMalusFixes{
	public void use(Entity ent) {
		if(ent.vie + 5 >= ent.vie_max)
			ent.vie = ent.vie_max;
		else
			ent.vie += 5;
	}
}
