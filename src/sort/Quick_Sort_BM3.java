package sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick_Sort_BM3 implements Sort{
	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		//���Ƚ�����˳����д���
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
    public static void sort(Comparable[] a, int lo, int hi){
    	//����Ѿ������ֱ꣬�ӷ���
        if(hi <= lo)    return;
        //v�ǻ��ֵ����֣�Ҳ���ǵ�һ��a[lo]
        Comparable v = a[lo];
        //��ʼ״̬��loָ����������ĵ�һ����i,pָ��ڶ�����q,j,hiָ�����һ��
        int p=lo+1, q=hi;
        int i=lo+1, j=hi;

        //ֱ��j < i
        while(i <= j){
        	//i�����ҵ���һ����С��a[lo]������j�����ҵ���һ��������a[lo]����������������
            while(Sort.less(a[i], v)){
                if(i == hi) break;
                i++;
            }
            while(Sort.less(v, a[j])){
                if(j == lo) break;
                j--;
            }
            Sort.exch(a, i, j);
			//���(a[i] == a[lo])������a[i]��a[p]��p++
            if(Sort.eq(a[i], v)) Sort.exch(a, ++p, i);
            //���(a[j] == a[lo])������a[j]��a[q]��q--
            if(Sort.eq(a[j], v)) Sort.exch(a, --q, j);
        }
        //���a[lo]�������ǰ�������棬ǰ���lo��p-1�������q+1��hi��
        //j��ָ��С��a[lo]�����һ����i��ָ�����a[lo]�ĵ�һ����
        //Ȼ��һֱ��a[p]��a[j]��������ǰ���v�ŵ��м�����p--j--,ֱ��pС��lo��
        for(int k = p-1; k >= lo; k--, j--)
        	Sort.exch(a, k, j);
        //��a[q]��a[i]�������Ѻ����v�ŵ��м䣬q++i++,ֱ��q����hi��
        for(int k = q+1; k <= hi; k++, i++)
        	Sort.exch(a, k, i);
        //��ʱj+1���м����ĵ�һ����i-1���м��������һ����
        sort(a, lo, j);
        sort(a, i, hi);
    }
}