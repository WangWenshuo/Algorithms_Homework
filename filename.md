---
title: 算法上机报告
tags: 新建,模板,小书匠
grammar_cjkRuby: true
---

## <center>第一次上机:渗漏</center>

### 一、题目描述
#### **1.渗漏模型**
渗漏模型是基于一个 N*N的网格矩阵，对于矩阵中的每一个点会有 *开放(open)* *阻塞(blocked)* 两种状态，此外，如果一个点能够通过一系列与之上下左右邻接的处于 *开放(open)* 状态的点连接到最顶端任意一点即认为其是一个处于 *满(full)* 状态的点,如果至少一个 *满点(full site)* 恰好处于最底下一行，那么就称这个系统是 *渗漏的(percolates)* 。 
#### **2.问题**
如果矩阵中每一个站点开放的概率独立都为 *p* (相应的阻塞的概率为 *1-p* )，估计次系统渗漏的可能性。著名科学实验证明，当矩阵足够大时，当 *p>p** 渗漏的概率会突变增加，达到极大的渗漏可能，此程序就是找到这个 *p**。
### 二、解决方法/算法以及解释
#### **1.解决方法**
我将每一个矩阵系统都映射到并查集中，建立并查集的过程即将此系统输入计算机，首先我们让所有的点处于 *阻塞(blocked)状态* ，之后随意的使得处于 *阻塞(blocked)* 的点变为 *开放的(open)*。之后便可以通过判断最后一行是否至少存在一点与第一行至少一点联通连判断是否 *渗漏的(percolates)* 。
#### **2.算法设计**
* 渗漏系统类定义（详细解释在注释中）
```
public class Percolation {
	// create N-by-N grid, with all sites blocked
	//以下是做一个N*N的网格系统，并且使得每一个点都处于blocked状态
	WeightedQuickUnionUF uf;
	boolean grid[][];
	int N;
	public Percolation(int N){
		try {
			if(N<0){
			//如果点数小于0抛出异常
				throw new IllegalArgumentException();
			}
			this.N=N;
			//为了使得下标号是从1到N，因此这里设置一个(N+1)(N+1)的并查集，对应着一个(N+1)(N+1)的数组，其中任意一维的小标为0的点都为冗余的，数组的目的是为了记录每个点的状态
			uf = new WeightedQuickUnionUF(N*N+1+2*N);
			grid = new boolean[N+1][N+1];
			//所有的点设置为blocked状态
			for (boolean[] bs : grid) {
				for (boolean b : bs) {
					b=false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
	// open site (row i, column j) if it is not already
	//如果点(i,j)没有处于open状态就将其开放，同时将其上下左右邻接的点进行并查集操作
	public void open(int i, int j){
/*		if(i<=0||j<=0){
			throw IndexOutOfBoundsException;
		}*/
		try {
			if(i<=0||j<=0||i>N||j>N){
				throw new IndexOutOfBoundsException();
			}
			if(!isOpen(i, j)){
				grid[i][j]=true;
			}
			if(grid[i-1][j]==true){
				uf.union(transform(i, j), transform(i-1, j));
			}
			if((i+1)<=N&&grid[i+1][j]==true){
				uf.union(transform(i, j), transform(i+1, j));
			}
			if(grid[i][j-1]==false){
				uf.union(transform(i, j), transform(i, j-1));
			}
			if((j+1)<=N&&grid[i][j+1]==false){
				uf.union(transform(i, j), transform(i, j+1));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	// is site (row i, column j) open?
	//判断点(i,j)是否处于开放状态，利用数组可以及时判断
	public boolean isOpen(int i, int j){
		try {
			if(i<=0||j<=0||i>N||j>N){
				throw new IndexOutOfBoundsException();
			}
			return grid[i][j];
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return (Boolean) null;
	}
	// is site (row i, column j) full?
	//判断点(i,j)是否处于full状态，只需要判断这一点是否和第一行中的某点联通即可
	public boolean isFull(int i, int j){
		try {
			if(i<=0||j<=0||i>N||j>N){
				throw new IndexOutOfBoundsException();
			}
			boolean flag = false;
			for(int k=1;k<=N;k++){
				if(uf.connected(transform(1, k), transform(i, j))){
					flag = true;
					break;
				}
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return (Boolean) null;
		
	}
	// does the system percolate?
	//判断这个点是否渗漏，如果最后一行存在一点处于full状态，该系统就是渗漏系统
	public boolean percolates(){
		boolean flag = false;
		for(int k=1;k<=N;k++){
			if(isFull(N, k)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	//transform from grid to uf
	//这个函数便于矩阵即二维数组和并查集合之间的转换
	private int transform(int i,int j) {
		return i*(N+1)+j;		
	}
}
```
* 蒙特卡洛测试类定义（详细解释在注释中）
```
public class PercolationStats {
		private double mean;
		private double stddev;
		private double confidenceLo;
		private double confidenceHi;
		// perform T independent computational experiments on an N-by-N grid
		//以下是在N*N的矩阵上进行T次独立渗漏实验
		public PercolationStats(	int N, int T){
		//传入两个参数，一个是N就是系统边长，一个是T实验次数
			try {
				if(N<0||T<0){
					throw new IllegalArgumentException();
				}
				double x[] = new double[T];
				for(int i=0;i<T;i++){
				//初始化一个渗漏系统
					Percolation percolation = new Percolation(N);
					boolean flag = true;
					int sum = 0;
					while (flag) {
						int first = StdRandom.uniform(N)+1;
						int second = StdRandom.uniform(N)+1;
						//任一选取一个点开放
						if(percolation.isOpen(first, second)){
							continue;
						}
						sum++;
						percolation.open(first, second);
						if(percolation.percolates()){
							//如果系统渗漏了，就计算概率
							x[i] = sum/(double)(N*N);
							flag=false;
						}
					}
				}
				//计算均值，方差，可信区间
				mean = StdStats.mean(x);
				stddev = StdStats.stddev(x);
				confidenceLo = mean-1.96*Math.sqrt(stddev)/Math.sqrt(T);
				confidenceHi = mean-1.96*Math.sqrt(stddev)/Math.sqrt(T);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		// sample mean of percolation threshold
		public double mean(){
			return this.mean;
		}
		// sample standard deviation of percolation threshold
		public double stddev(){
			return stddev;
		}
		// returns lower bound of the 95% confidence interval
		public double confidenceLo(){
			return this.confidenceLo;
		}
		// returns upper bound of the 95% confidence interval
		public double confidenceHi(){
			return this.confidenceHi;
		}
		// test client, described below
		//这是蒙特卡洛的测试端
		public static void main(String[] args){
			int N = Integer.parseInt(args[0]);
			int T = Integer.parseInt(args[1]);
			PercolationStats percolationStats = new PercolationStats(N, T);
			System.out.println("N="+N+","+"T="+T+":");
			System.out.println("mean="+percolationStats.mean());
			System.out.println("stddev="+percolationStats.stddev());
			System.out.println("95% confidence interval="+percolationStats.confidenceLo()+","+percolationStats.confidenceHi());
		}

}
```
### 三、实验结果和分析
#### *1.N=200 T=100*
![enter description here][1]
#### *2.N=2   T=10000*
![enter description here][2]
#### *3.N=2   T=100000*
![enter description here][3]
#### *4.N=300 T=200*
![enter description here][4]

**由以上测试数据可知，在大量实验情况下，p稳定在0.59。**


  [1]: ./images/200%20100.png "200 100.png"
  [2]: ./images/2%2010000.png "2 10000.png"
  [3]: ./images/2%20100000.png "2 100000.png"
  [4]: ./images/SGOPJ%295IOC%29%7B6JOZ65E%29GJT.png "SGOPJ&#41;5IOC&#41;{6JOZ65E&#41;GJT.png"
  