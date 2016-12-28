package sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Main{
	public static void main(String[] args) {
		int N=10;
		Double a[] = new Double[N];
		for(int i=0;i<N;i++){
			a[i]=StdRandom.uniform();
		}
		Sort.show(a);
		new Merge_Sort3().sort(a);
		Sort.show(a);
	}
}
