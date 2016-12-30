package sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick_Sort_Dj3 implements Sort{

	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		//首先将数字顺序进行打乱
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
    public static void sort(Comparable[] a, int lo, int hi){
    	//如果已经排序完，直接返回
        if(hi <= lo) return;
        //有三个指针，一个是lt最低位，一个是gt最高位，还有一个i从头扫到尾
        int lt = lo, i = lo, gt = hi;
        //v是划分的数字，也就是最初的a[lo]
        Comparable v = a[lo];
        while(i <= gt){
			//如果a[i]<v那么交换a[lt]和a[i]，lt++,i++
            if(Sort.less(a[i], v)) Sort.exch(a, lt++, i++);
            //如果a[i]>v那么交换a[gt]和a[i]，i不变gt--
            else if(Sort.less(v, a[i])) Sort.exch(a, i, gt--);
            //如果a[i]==v那么i++,直到i>gt
            else i++;
        }
        //那么此时it是中间相等段落（都是v）的最低位，gt是最高位。接下来排序lo到it-1和gt+1到hi即可
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

}