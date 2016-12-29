package sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Main{
	public static void main(String[] args) {
		int N=2000;
		Double a[] = new Double[N];
		for(int i=0;i<N;i++){
			a[i]=StdRandom.uniform();
		}
		Stopwatch timer = new Stopwatch();
		Sort xSort=new Quick_Sort_Dj2();
		xSort.sort(a);
		double time = timer.elapsedTime();
		System.out.print("The array is ");
		if(!Sort.isSorted(a))
			System.out.print(" not ");
		System.out.println("sorted by "+xSort.getClass()+" and the time is "+time+"s");
	}
}
