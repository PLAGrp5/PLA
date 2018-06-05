package edu.ricm3.game.sample;

public class ConditionFree extends Condition{
	Entity Tank;
	public ConditionFree(Entity T) {
		this.Tank = T;
	}
	public boolean eval(char X) {
		switch (X) {
			case 'U' : 
				return map(Tank.coord) == "F";
				break;
			case 'D' : 
				return map(Tank.coord) == "F";
				break;
			case 'L' : 
				return map(Tank.coord) == "F";
				break;
			case 'R' : 
				return map(Tank.coord) == "F";
				break;
		}
		
	}

}
