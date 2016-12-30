package sort;

import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Main{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		testSort(new Insert_sort(), x);
		testSort(new Merge_Sort1(), x);
		testSort(new Merge_Sort3(), x);
		testSort(new Quick_Sort(), x);
		testSort(new Quick_Sort_Dj3(), x);
		testSort(new QuickBentleyMcllroy(), x);
		testSort(new Shell_Sort(), x);
		testSort(new Select_Sort(), x);
	}
	public static void testSort(Sort x,int m){
		int N=m;
		Double a[] = new Double[N];
		for(int i=0;i<N;i++){
			a[i]=StdRandom.uniform();
		}
		Stopwatch timer = new Stopwatch();
		Sort xSort=x;
		xSort.sort(a);
		double time = timer.elapsedTime();
		System.out.print("The array is ");
		if(!Sort.isSorted(a))
			System.out.print(" not ");
		System.out.println("sorted by "+xSort.getClass()+" and the time is "+time+"s");

	}
}
