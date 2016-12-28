package The3rd;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Point {
	private final int number;
	private final int x;
	private final int y;
	public Point(int number,int x,int y) {
		// TODO Auto-generated constructor stub
		this.number=number;
		this.x=x;
		this.y=y;
	}
	public int getNumber() {
		return number;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Point point = (Point)obj;
		return point.number==this.number;
	}
	public static Point getPoint(int number,ArrayList<Point> points){
		for (Point point : points) {
			if(point.number==number)
				return point;
		}
		return null;
	}
}
