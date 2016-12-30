package sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick_Sort_Dj3 implements Sort{

	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		//���Ƚ�����˳����д���
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
    public static void sort(Comparable[] a, int lo, int hi){
    	//����Ѿ������ֱ꣬�ӷ���
        if(hi <= lo) return;
        //������ָ�룬һ����lt���λ��һ����gt���λ������һ��i��ͷɨ��β
        int lt = lo, i = lo, gt = hi;
        //v�ǻ��ֵ����֣�Ҳ���������a[lo]
        Comparable v = a[lo];
        while(i <= gt){
			//���a[i]<v��ô����a[lt]��a[i]��lt++,i++
            if(Sort.less(a[i], v)) Sort.exch(a, lt++, i++);
            //���a[i]>v��ô����a[gt]��a[i]��i����gt--
            else if(Sort.less(v, a[i])) Sort.exch(a, i, gt--);
            //���a[i]==v��ôi++,ֱ��i>gt
            else i++;
        }
        //��ô��ʱit���м���ȶ��䣨����v�������λ��gt�����λ������������lo��it-1��gt+1��hi����
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

}