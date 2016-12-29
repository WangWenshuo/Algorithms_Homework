---
title: 算法上机题目2
tags: 渗漏
grammar_cjkRuby: True
---

### 一、题目描述
实现各种排序算法并且分析性能
### 二、解决方法算法和描述
#### 0.各个排序算法需要实现的接口
```
interface Sort {
	public abstract void sort(Comparable[] a);
	//比较两个数据的大小，若第一个数据比第二个数据小则返回真，否则就返回假
	public static boolean less(Comparable u,Comparable v){
		return u.compareTo(v)<0;
	}
	//比较两个数据是否相等，若相等则返回真，否则就返回假
	public static boolean eq(Comparable u,Comparable v){
		return u.compareTo(v)==0;
	}
	//交换数组中某两个元素的值
	public static void exch(Comparable a[],int i,int j){
		Comparable t = a[i];
		a[i]=a[j];
		a[j]=t;
	}
	//打印出某个数组的各个元素的值
	public static void show(Comparable a[]){
		int N = a.length;
		for(int i=0;i<N;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	//判断数组是否有序
	public static boolean isSorted(Comparable a[]){
		int N = a.length;
		for(int i=1;i<N;i++){
			if(less(a[i], a[i-1])){
				return false;
			}
		}
		return true;
	}
	//判断数组的某部分是否有序
	public static boolean isSorted(Comparable a[],int lo,int hi){
		for(int i=lo;i<hi;i++){
			if(less(a[i+1], a[i])){
				return false;
			}
		}
		return true;
	}
}
```
#### 1.插入排序
#### 2.归并排序(递归排序和自底向上)
#### 3.快排
```
public class Quick_Sort implements Sort{

	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		//首先将数字顺序进行打乱
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	//将a中lo到hi位置的数字进行排序
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		//用j位置的数据划分整个数组的数据，比a[j]小的数据在其左边，比a[j]大的数据在其右边。
		int j = partition(a, lo, hi);
		//再把j左边和右边的数据进行排序
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	
    private static int partition(Comparable[] a, int start, int end) {  
        //p位置是第一个数据，用来进行划分
        int p = start, i, j;
        for (i = start, j = end; i < j;) {
        	//i从前往后扫，直到找到一个比a[p]大的数据或者i不再小于j。
            while(Sort.less(a[i], a[p]) && i < j) i++;
        	//j从后往前扫，直到找到一个比a[p]小的数据或者i不再小于j。
            while(Sort.less(a[p], a[j]) && i < j) j--;
            if(i >= j) {
            //如果此时ij已经相遇，那么将用来划分的数据放到最中间，并返回它的位置。
            	Sort.exch(a, p, i);
            	return i;
            }else{
            //如果i小于j，那么交换ij位置的数据。
            	Sort.exch(a, i, j);
            }
        }
        return i;
    } 

}
```
#### 4.Quicksort with Dijkstra 3-way partitioning
```
public class Quick_Sort_Dj2 implements Sort{

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
            int cmp = a[i].compareTo(v);
			//如果a[i]<v那么交换a[lt]和a[i]，lt++,i++
            if(cmp < 0) Sort.exch(a, lt++, i++);
            //如果a[i]>v那么交换a[gt]和a[i]，i不变gt--
            else if(cmp > 0) Sort.exch(a, i, gt--);
            //如果a[i]==v那么i++,直到i>gt
            else	i++;
        }
        //那么此时it是中间相等段落（都是v）的最低位，gt是最高位。接下来排序lo到it-1和gt+1到hi即可
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

}
```
#### 5.Quicksort with Bentley-McIlroy 3-way partitioning.
```
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
```

### 三、实验结果和分析
