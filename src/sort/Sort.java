package sort;

interface Sort {
	public abstract void sort(Comparable[] a);
	public static boolean less(Comparable u,Comparable v){
		return u.compareTo(v)<0;
	}
	public static void exch(Comparable a[],int i,int j){
		Comparable t = a[i];
		a[i]=a[j];
		a[j]=t;
	}
	public static void show(Comparable a[]){
		int N = a.length;
		for(int i=0;i<N;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	public static boolean isSorted(Comparable a[]){
		int N = a.length;
		for(int i=1;i<N;i++){
			if(less(a[i], a[i-1])){
				return false;
			}
		}
		return true;
	}
	public static boolean isSorted(Comparable a[],int lo,int hi){
		for(int i=lo;i<hi;i++){
			if(less(a[i+1], a[i])){
				return false;
			}
		}
		return true;
	}
}
