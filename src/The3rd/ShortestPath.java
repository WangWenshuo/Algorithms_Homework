package The3rd;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ShortestPath {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In("/Users/Administrator/Desktop/Algorithms_Homework/src/The3rd/usa.txt");
		//System.out.println(args[0]);
		//System.out.println(s);
		Graph G = new Graph(in);
		int x = in.readInt();
		int y = in.readInt();
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
