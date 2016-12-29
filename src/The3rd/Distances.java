package The3rd;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Distances {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In("C:\\Users\\Administrator\\Desktop\\usa.txt");
		//System.out.println(args[0]);
		//System.out.println(s);
		Graph G = new Graph(in);
		int x = 1;
		int y = 70;
		int s = x;
		Dijkstra dijkstra = new Dijkstra(G, Point.getPoint(s, G.getPoints()));
		for(int t=0;t<G.getV();t++){
			StdOut.print(s+" to "+t);
			StdOut.printf(" (%4.2f) ", dijkstra.distTo(t));
			StdOut.println();
		}
	}
}
