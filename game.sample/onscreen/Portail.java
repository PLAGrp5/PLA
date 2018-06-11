package onscreen;

import java.util.ArrayList;
import java.util.List;

public class Portail {
	List<Point> list = new ArrayList<Point>();
	int n;
	public Portail() {
		n = 0;
	}
	
	public void Add(Point p) {
		this.list.add(p);
		n++;
	}
	
	public Point Get(int i) {
		return this.list.get(i);
	}
	
	public int NombrePortail() {
		return n;
	}
}
