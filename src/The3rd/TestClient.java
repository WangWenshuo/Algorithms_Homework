package The3rd;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestClient {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In("/Users/Administrator/Desktop/Algorithms_Homework/src/The3rd/data.txt");
		//System.out.println(args[0]);
		int s = Integer.parseInt(args[1]);
		//System.out.println(s);
		Graph G = new Graph(in);
		int x = in.readInt();
		int y = in.readInt();
		Dijkstra dijkstra = new Dijkstra(G, Point.getPoint(s, G.getPoints()));
		for(int t=0;t<G.getV();t++){
			StdOut.print(s+" to "+t);
			StdOut.printf(" (%4.2f): ", dijkstra.distTo(t));
			if(dijkstra.hasPathTo(t))
				for(Edge e:dijkstra.pathTo(t))
					StdOut.print(e+"  ");
			StdOut.println();
		}
	}
}
