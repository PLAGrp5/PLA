package automate;

import onscreen.*;

public class MyDir extends Condition {
		public MyDir(String dir) {
			this.dir = dir.charAt(0);
		}
		
		public boolean eval(Entity e) {
			return e.dir == this.dir;
		}
}
