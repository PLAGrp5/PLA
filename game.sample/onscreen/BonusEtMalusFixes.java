package onscreen;

public abstract class BonusEtMalusFixes {
	char type;
	//renvoie false si inventaire plein
	public boolean prendre(Entity ent) {
		if (ent.inventaire[0] == null)
			ent.inventaire[0] = this;
		else if (ent.inventaire[1] == null)
			ent.inventaire[1] = this;
		else if (ent.inventaire[2] == null)
			ent.inventaire[2] = this;
		else
			return false;
		return true;
	}
	public abstract void jeter(Map m, Entity ent);
}
