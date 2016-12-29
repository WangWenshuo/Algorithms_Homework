package sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick_Sort implements Sort{

	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	
    private static int partition(Comparable[] a, int start, int end) {  
        int p = start, i, j;
        for (i = start, j = end; i < j;) {
            while(Sort.less(a[i], a[p]) && i < j) i++;
            while(Sort.less(a[p], a[j]) && i < j) j--;
            if(i >= j) {
            	Sort.exch(a, p, i);
            	return i;
            }else{
            	Sort.exch(a, i, j);
            }
        }
        return i;
    } 

}
