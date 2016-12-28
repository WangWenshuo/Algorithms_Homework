package sort;

public class Merge_Sort1 implements Sort{

	Comparable aux[];
	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		int N = a.length;
		aux=new Comparable[N];
		sort(a, 0, a.length-1);
	}
	public void sort(Comparable[] a,int lo,int hi){
		if(lo>=hi)
			return;
		int mid=lo+(hi-lo)/2;
		sort(a, lo, mid);
		sort(a, mid+1, hi);
		if(Sort.less(a[mid+1], a[mid]))
			merge(a, lo, mid, hi);
	}
	public void merge(Comparable a[],int lo,int mid,int hi){
		int N = a.length;
		int i=lo;
		int j=mid+1;
		for(int k=lo;k<=hi;k++)
			aux[k]=a[k];
		for(int k=lo;k<=hi;k++){
			if(i>mid) a[k]=aux[j++];
			else if(j>hi) a[k]=aux[i++];
			else if(Sort.less(aux[i], aux[j])) a[k]=aux[i++];
			else a[k]=aux[j++];
		}
	}

}
