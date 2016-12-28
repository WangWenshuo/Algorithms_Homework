package The3rd;

import java.awt.event.MouseWheelEvent;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class Dijkstra {
	private Edge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
    public Dijkstra(Graph G, Point s) {
        for (Edge e : G.getEdges()) {
            if (e.getWeight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
            
        }

        distTo = new double[G.getV()];
        edgeTo = new Edge[G.getV()];
        for (int v = 0; v < G.getV(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s.getNumber()] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(G.getV());
        pq.insert(s.getNumber(), distTo[s.getNumber()]);
        while (!pq.isEmpty()) {
        	relax(G, pq.delMin());
        }

        // check optimality conditions
        //assert check(G, s);
    }

    // relax edge e and update pq if changed
    private void relax(Graph G,int v) {
    	for (Edge e:G.getAdjV(v)) {
			int w = e.getY().getNumber();
			if(distTo[w]>distTo[v]+e.getWeight()){
				distTo[w]=distTo[v]+e.getWeight();
				edgeTo[w]=e;
				if(pq.contains(w)) pq.change(w, distTo[w]);
				else {
					pq.insert(w, distTo[w]);
				}
			}
		}
    }


    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.getX().getNumber()]) {
            path.push(e);
        }
        return path;
    }
}
