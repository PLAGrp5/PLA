package edu.ricm3.game.sample;
import java.lang.String;
import java.lang.reflect.*;

public class Action {

	public String met;
	public String arg;
		
	Action() {	
	}
	
	Action(String cdt) {
		this.met = cdt;
	}
	
	public void exec_act() {
		try {
			Method method = this.met.getClass().getMethod(met, String.class);
			try {
				method.invoke(arg);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return;
	}

}
