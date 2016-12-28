package The3rd;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
	private final int V;
	private int E;
	private Bag<Edge>[] adj;
	private ArrayList<Point> points;
	public Graph(int V) {
		// TODO Auto-generated constructor stub
		this.V=V;
		this.E=0;
		adj = (Bag<Edge> []) new Bag[V];
		for(int i=0;i<V;i++){
			adj[i] = new Bag<Edge>();
		}
		points = new ArrayList();
	}
	public Graph(In in){
		this(in.readInt());
		int E=in.readInt();
		int V=this.V;
		for(int i=0;i<V;i++){
			int v = in.readInt();
			int vx = in.readInt();
			int vy = in.readInt();
			Point vPoint = new Point(v, vx, vy);
			points.add(vPoint);
		}
		for(int k=0;k<E;k++){
			int xx = in.readInt();
			int yy = in.readInt();
			addEdge(new Edge(xx, yy, points));
			addEdge(new Edge(yy, xx, points));
		}
/*		for(int i=0;i<E;i++){
			int v = in.readInt();
			int vx = in.readInt();
			int vy = in.readInt();
			Point vPoint = new Point(v, vx, vy);
			int w = in.readInt();
			int wx = in.readInt();
			int wy = in.readInt();
			Point wPoint = new Point(w, wx, wy);
			points.add(vPoint);
			points.add(wPoint);
			addEdge(new Edge(vPoint, wPoint));
		}*/
	}
	public int getE() {
		return E;
	}
	public Bag<Edge>[] getAdj() {
		return adj;
	}
	public Iterable<Edge> getAdjV(int v){
		return adj[v];
	}
	public int getV() {
		return V;
	}
	public ArrayList<Point> getPoints(){
		return points;
	}
	public void addPoint(Point point){
		points.add(point);
	}
	public void addEdge(Edge e){
		Point x=e.getEither();
		//Point y=e.getOther(x);
		adj[x.getNumber()].add(e);
		//adj[y.getNumber()].add(e);	
	}
	public Iterable<Edge> getEdges(){
		Bag<Edge> b = new Bag<Edge>();
		for(int v=0;v<V;v++){
			for(Edge e:adj[v]){
				//if(e.getOther(Point.getPoint(v, points)).getNumber()>v)
					b.add(e);
			}
		}
		return b;
	}
}
