package sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick_Sort_BM3 implements Sort{
	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		//首先将数字顺序进行打乱
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
    public static void sort(Comparable[] a, int lo, int hi){
    	//如果已经排序完，直接返回
        if(hi <= lo)    return;
        //v是划分的数字，也就是第一个a[lo]
        Comparable v = a[lo];
        //初始状态：lo指向待排序段落的第一个，i,p指向第二个，q,j,hi指向最后一个
        int p=lo+1, q=hi;
        int i=lo+1, j=hi;

        //直到j < i
        while(i <= j){
        	//i向右找到第一个不小于a[lo]的数，j向左找到第一个不大于a[lo]的数，交换它们俩
            while(Sort.less(a[i], v)){
                if(i == hi) break;
                i++;
            }
            while(Sort.less(v, a[j])){
                if(j == lo) break;
                j--;
            }
            Sort.exch(a, i, j);
			//如果(a[i] == a[lo])，交换a[i]和a[p]，p++
            if(Sort.eq(a[i], v)) Sort.exch(a, ++p, i);
            //如果(a[j] == a[lo])，交换a[j]和a[q]，q--
            if(Sort.eq(a[j], v)) Sort.exch(a, --q, j);
        }
        //最后a[lo]会堆在最前面和最后面，前面从lo到p-1，后面从q+1到hi，
        //j会指向小于a[lo]的最后一个，i会指向大于a[lo]的第一个。
        //然后一直将a[p]和a[j]交换，把前面的v放到中间来，p--j--,直到p小于lo，
        for(int k = p-1; k >= lo; k--, j--)
        	Sort.exch(a, k, j);
        //将a[q]和a[i]交换，把后面的v放到中间，q++i++,直到q大于hi。
        for(int k = q+1; k <= hi; k++, i++)
        	Sort.exch(a, k, i);
        //这时j+1是中间段落的第一个，i-1是中间段落的最后一个。
        sort(a, lo, j);
        sort(a, i, hi);
    }
}