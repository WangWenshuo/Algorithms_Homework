package sort;

public class Merge_Sort2 implements Sort{
	private Comparable aux[];
//When process small subarray,use insert or select sort	 
	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		int N = a.length;
		aux=new Comparable[N];
		sort(a, 0, N-1);
	}
	public void sort(Comparable[] a,int lo,int hi){
		if(lo>=hi)
			return;
		int mid = lo+(hi-lo)/2;
		if(Sort.less(a[mid], a[mid+1]))
			return;
		if(mid-lo>=4){
			sort(a, lo, mid);
			//System.out.println("1"+Sort.isSorted(a,lo,mid));
		}
		else{ 
			new Insert_sort().sort(a, lo, mid);
			//System.out.println(Sort.isSorted(a,lo,mid));
		}
		if(hi-mid>=5){
			sort(a, mid+1, hi);
			//ystem.out.println("2"+Sort.isSorted(a,mid+1,hi));
		}
		else{
			new Insert_sort().sort(a, mid+1, hi);
			//System.out.println(Sort.isSorted(a, mid+1, hi));
		}
		//System.out.println("1:"+Sort.isSorted(a,lo,hi));
		merge(a, lo, mid, hi);
		//System.out.println("2:"+Sort.isSorted(a,lo,hi));
	}
	public void merge(Comparable[] a,int lo,int mid,int hi){
		int m=lo;
		int n=mid+1;
		for(int i=lo;i<=hi;i++){
			aux[i]=a[i];
		}
		for(int k=lo;k<=hi;k++){
			if(m>mid) a[k]=aux[n++];
			else if(n>hi) a[k]=aux[m++];
			else if(Sort.less(a[m], a[n])) a[k]=aux[m++];
			else a[k]=aux[n++];
		}
	}

}
