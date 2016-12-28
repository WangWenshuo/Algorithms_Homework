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
#### 4.迪杰斯特拉二路排序
#### 5.
### 三、实验结果和分析
