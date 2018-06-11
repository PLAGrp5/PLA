package automate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import onscreen.Entity;
import onscreen.Map;

import java.lang.Object;

public abstract class Condition {

	char dir;
	public abstract boolean eval(Entity ent);
	
	/*public String cond_met1;
	public String cond_arg1[];
	public String comp;
	public String cond_met2;
	public String cond_arg2[];
	
	public Condition() {	
	}
	
	Condition(String met1, String met2, String arg1[], String arg2[], String cmp) {
		this.cond_met1 = met1;
		this.cond_arg1 = arg1;
		this.comp = cmp;
		this.cond_met2 = met2;
		this.cond_arg2 = arg2;
	}
	
	public boolean eval() {
		return true;
	}
	
	public boolean exec_cond() {
		Object res1 = null;
		Object res2 = null;
		
		if (cond_met1 != "") {
			try {
				Method method = this.cond_met1.getClass().getMethod(cond_met1, String.class);
				try {
					res1 = method.invoke((Object)cond_met1,(Object)cond_arg1);
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
			if (cond_met2 == "") {
				switch(this.comp) {
				case "":
					return (boolean)res1;
				case "!":
					return !((boolean)res1);
				default:
					return true;
				}
			}
		}
		if (cond_met2 != "") {
			try {
				Method method = this.cond_met2.getClass().getMethod(cond_met2, String.class);
				try {
					res2 = method.invoke((Object)cond_met2, (Object)cond_arg2);
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
		}
		switch(this.comp) {
		case "==":
			return (res1 == res2);
		case "<":
			return ((float)res1 < (float)res2);
		case ">":
			return ((float)res1 > (float)res2);
		case "<=":
			return ((float)res1 <= (float)res2);
		case ">=":
			return ((float)res1 >= (float)res2);
		case "!=":
			return ((float)res1 != (float)res2);
		case "&":
			return ((boolean)res1 & (boolean)res2);
		case "|":
			return ((boolean)res1 | (boolean)res2);
		default:
			return true;
		}		
	}
	
	public int addition(int a, int b) {
		return (a + b);
	}
	
	public static void main(String args[]) {
		Condition cond = new Condition();
		
		cond.cond_met1 = "edu.ricm3.game.sample.Condition.addition";
		cond.cond_met2 = "edu.ricm3.game.sample.Condition.addition";
		cond.cond_arg1 = new String[2];
		cond.cond_arg1[0] = "2";
		cond.cond_arg1[1] = "3";
		cond.cond_arg2 = new String[2];
		cond.cond_arg1[0] = "1";
		cond.cond_arg1[1] = "4";
		cond.comp = "==";
		
		System.out.print(cond.exec_cond());
	}*/
	
}