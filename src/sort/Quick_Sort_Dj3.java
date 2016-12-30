package sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick_Sort_Dj3 implements Sort{

	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
    public static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int lt = lo, i = lo, gt = hi;
        Comparable v = a[lo];
        while(i <= gt){
            if(Sort.less(a[i], v)) Sort.exch(a, lt++, i++);
            else if(Sort.less(v, a[i])) Sort.exch(a, i, gt--);
            else i++;
        }   
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

}