package The3rd;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ShortestPath {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In("/home/wangxiaoxiong/Documents/Algorithms_Homework/src/The3rd/usa.txt");
		//System.out.println(args[0]);
		//System.out.println(s);
		Graph G = new Graph(in);

		Iterable<Edge> edges=G.getEdges();
/*		for (Edge edge : edges) {
			Point point1 = edge.getEither();
			double x0 = point1.getX();
			double y0 = point1.getY();
			Point point2 = edge.getOther(point1);
			double x1 = point2.getX();
			double y1 = point2.getY();
			StdDraw.setXscale(0,10000);
			StdDraw.setYscale(0,10000);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.line(x0, y0, x1, y1);
		}*/

		int x = 0;
		int y = 70;

		int s=x;
		Dijkstra dijkstra = new Dijkstra(G, Point.getPoint(s, G.getPoints()));
		StdOut.print(s+" to "+y);
		StdOut.printf(" (%4.2f): ", dijkstra.distTo(y));
		if(dijkstra.hasPathTo(y))
			for(Edge e:dijkstra.pathTo(y))
				StdOut.print(e+"  ");
		StdOut.println();
	}
}
