package sort;

public class Merge_Sort3 implements Sort{
	//Merge Sort From bottom to up
	private Comparable[] aux;
	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		int N = a.length;
		aux=new Comparable[N];
		for(int sz=1;sz<N;sz=sz+sz)
			for(int lo=0;lo<N-sz;lo+=sz+sz)
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
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
