package sort;

public class Merge_Sort3 implements Sort{
	//Merge Sort From bottom to up
	private Comparable[] aux;
	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		int N = a.length;
		aux=new Comparable[N];
		for(int step=2;step<=N;step+=step){
			for(int i=0;i<N;i+=step){
				merge(a, i,Math.min(i+step-1, N-1));
			}
		}
	}
	public void merge(Comparable[] a,int lo,int hi){
		int mid=lo+(hi-lo)/2;
		int i=lo;
		int j=mid+1;
		for(int k=lo;k<=hi;k++){
			aux[k]=a[k];
		}
		for(int k=lo;k<=hi;k++){
			if(i>mid) a[k]=aux[j++];
			else if(j>hi) a[k]=aux[i++];
			else if(Sort.less(a[i], a[j])) a[k]=aux[i++];
			else a[k]=aux[j++];
		}
	}
	
}
