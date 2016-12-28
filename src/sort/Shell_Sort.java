package sort;

public class Shell_Sort implements Sort{

	@Override
	public void sort(Comparable[] a) {
		// TODO Auto-generated method stub
		int N = a.length;
		int h=1;
		while(h<N/3) h=3*h+1;
		while(h>=1){
			for(int i=0;i<N;i++){
				for(int j=i;j>=h&&Sort.less(a[j], a[j-h]);j-=h){
					Sort.exch(a, j-h, j);
				}
			}
			h=h/3;
		}
	}

}
