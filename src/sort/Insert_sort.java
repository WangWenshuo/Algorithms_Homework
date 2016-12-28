package sort;

public class Insert_sort implements Sort{

	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		int N = a.length;
		for(int i=1;i<N;i++){
			for(int j=i;j>0;j--){
				if(Sort.less(a[j], a[j-1])){
					Sort.exch(a, j-1, j);
				}else {
					break;
				}
			}
		}
	}
	public void sort(Comparable[] a,int lo,int hi){
		for(int i=lo;i<hi;i++){
			for(int j=i+1;j>lo&&Sort.less(a[j], a[j-1]);j--){
					Sort.exch(a, j-1, j);
			}
		}
		//System.out.println("3"+Sort.isSorted(a,lo,hi));
	}
	
}
