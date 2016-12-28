package The3rd;


import java.lang.*;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
public class Edge implements Comparable<Edge>{
	private final Point x;
	private final Point y;
	private final double weight;
	public Edge(int xx,int yy,ArrayList<Point> points) {
		// TODO Auto-generated constructor stub
		Point x = Point.getPoint(xx, points);
		Point y = Point.getPoint(yy, points);
		this.x=x;
		this.y=y;
		this.weight=Math.sqrt((x.getX()-y.getX())*(x.getX()-y.getX())+(x.getY()-y.getY())*(x.getY()-y.getY()));
	}
	
	public Edge(Point x, Point y) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.weight=Math.sqrt((x.getX()-y.getX())*(x.getX()-y.getX())+(x.getY()-y.getY())*(x.getY()-y.getY()));
	}

	public Point getX() {
		return x;
	}

	public Point getY() {
		return y;
	}

	public double getWeight() {
		return weight;
	}
	public Point getEither(){
		return x;
	}
	public Point getOther(Point x){
		if(this.x.equals(x))
			return x;
		else if(this.y.equals(x))
			return y;
		else 
			throw new RuntimeErrorException(null, "Inconsistent edge");
	}
	
	@Override
	public int compareTo(Edge that) {
		// TODO Auto-generated method stub
		if(this.weight<that.weight) return -1;
		else if(this.weight>that.weight) return 1;
		else return 0;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d-%d %.2f", x.getNumber(),y.getNumber(),weight);
	}
	public String printString() {
		// TODO Auto-generated method stub
		return String.format("%d-%d ", x.getNumber(),y.getNumber());
	}
	
}
