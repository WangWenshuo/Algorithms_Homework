package first_homework;

import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	// create N-by-N grid, with all sites blocked
	WeightedQuickUnionUF uf;
	boolean grid[][];
	int N;
	public Percolation(int N){
		try {
			if(N<0){
				throw new IllegalArgumentException();
			}
			this.N=N;
			uf = new WeightedQuickUnionUF(N*N+2+3*N);
			grid = new boolean[N+2][N+1];
			for (boolean[] bs : grid) {
				for (boolean b : bs) {
					b=false;
				}
			}
			for(int i=0;i<N+1;i++){
				open(0,i);
				open(N+1,i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
	// open site (row i, column j) if it is not already
	public void open(int i, int j){
/*		if(i<=0||j<=0){
			throw IndexOutOfBoundsException;
		}*/
		try {
			if(i<0||j<0||i>N+1||j>N+1){
				throw new IndexOutOfBoundsException();
			}
			if(!isOpen(i, j)){
				grid[i][j]=true;
			}
			if(i>0&&grid[i-1][j]==true){
				uf.union(transform(i, j), transform(i-1, j));
			}
			if((i+1)<=N&&grid[i+1][j]==true){
				uf.union(transform(i, j), transform(i+1, j));
			}
			if(j>0&&grid[i][j-1]==false){
				uf.union(transform(i, j), transform(i, j-1));
			}
			if((j+1)<=N&&grid[i][j+1]==false){
				uf.union(transform(i, j), transform(i, j+1));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j){
		try {
			if(i<0||j<0||i>N+1||j>N+1){
				throw new IndexOutOfBoundsException();
			}
			return grid[i][j];
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return (Boolean) null;
	}
	// is site (row i, column j) full?
	public boolean isFull(int i, int j){
		try {
			if(i<0||j<0||i>N+1||j>N+1){
				throw new IndexOutOfBoundsException();
			}
			uf.connected(transform(0, 0), transform(i, j));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return (Boolean) null;
		
	}
	// does the system percolate?
	public boolean percolates(){
		return uf.connected(transform(0, 0), transform(N+1, 0));
	}
	//transform from grid to uf
	private int transform(int i,int j) {
		return i*(N+1)+j;		
	}
	// test client, optional
	/*public static void main(String[] args){
/*		Percolation percolation = new Percolation(10);
		System.out.println(percolation.percolates());
		for(int i=1;i<=10;i++){
			percolation.open(i, 10);
		}
		System.out.println(percolation.percolates());
	}*/
}
