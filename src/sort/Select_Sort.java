package sort;

public class Select_Sort implements Sort{

	@Override
	public  void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		int N = a.length;
		for(int i=0;i<N;i++){
			int min = i;
			for(int j=i+1;j<N;j++){
				if(Sort.less(a[j], a[min])){
					min=j;
				}
			}
			Sort.exch(a, i, min);
		}
	}

}
