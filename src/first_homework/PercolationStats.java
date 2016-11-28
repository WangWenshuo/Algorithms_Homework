package first_homework;

//import java.util.Scanner;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
		private double mean;
		private double stddev;
		private double confidenceLo;
		private double confidenceHi;
		// perform T independent computational experiments on an N-by-N grid
		public PercolationStats(	int N, int T){
			try {
				if(N<0||T<0){
					throw new IllegalArgumentException();
				}
				double x[] = new double[T];
				for(int i=0;i<T;i++){
					Percolation percolation = new Percolation(N);
					boolean flag = true;
					int sum = 0;
					while (flag) {
						int first = StdRandom.uniform(N)+1;
						int second = StdRandom.uniform(N)+1;
						if(percolation.isOpen(first, second)){
							continue;
						}
						sum++;
						percolation.open(first, second);
						if(percolation.percolates()){
							x[i] = sum/(double)(N*N);
							//System.out.println(x[i]);
							flag=false;
						}
					}
				}
				mean = StdStats.mean(x);
				stddev = StdStats.stddev(x);
				confidenceLo = mean-1.96*Math.sqrt(stddev)/Math.sqrt(T);
				confidenceHi = mean-1.96*Math.sqrt(stddev)/Math.sqrt(T);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		// sample mean of percolation threshold
		public double mean(){
			return this.mean;
		}
		// sample standard deviation of percolation threshold
		public double stddev(){
			return stddev;
		}
		// returns lower bound of the 95% confidence interval
		public double confidenceLo(){
			return this.confidenceLo;
		}
		// returns upper bound of the 95% confidence interval
		public double confidenceHi(){
			return this.confidenceHi;
		}
		// test client, described below
		public static void main(String[] args){
			int N = Integer.parseInt(args[0]);
			int T = Integer.parseInt(args[1]);
			PercolationStats percolationStats = new PercolationStats(N, T);
			System.out.println("N="+N+","+"T="+T+":");
			System.out.println("mean="+percolationStats.mean());
			System.out.println("stddev="+percolationStats.stddev());
			System.out.println("95% confidence interval="+percolationStats.confidenceLo()+","+percolationStats.confidenceHi());
		}

}
