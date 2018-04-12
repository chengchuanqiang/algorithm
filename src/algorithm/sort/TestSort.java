package algorithm.sort;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class TestSort {
	
	public static void main(String[] args) {
		Map<String, Map<Integer, Long>> map = new LinkedHashMap<String, Map<Integer,Long>>();
		init(map);
		int i = 10_000_000;
		int n = 100_000_000;
		int step = 10_000_000;
		long s = 0;
		long e = 0;
		int num = 1;
		for (; i <= n; i = i + step) {
			int[] source = new int[i];
			int[] result = new int[i];
			for (int j = 0; j < i; j++) {
				source[j] = new Random().nextInt() % i;
			}
			//print(source);
			
			System.out.println("---------------------------第  "+ num++ +" 次，数据量：" + i + "----------------------------");
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			insertSort(result);
			e = System.currentTimeMillis();
			System.out.println("插入排序时间：" + (e - s));
			map.get("insertSort").put(i, (e - s));
			//print(result);
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			bubbleSort(result);
			e = System.currentTimeMillis();
			System.out.println("冒泡排序时间：" + (e - s));
			map.get("bubbleSort").put(i, (e - s));
			//print(result);
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			selectSort(result);
			e = System.currentTimeMillis();
			System.out.println("选择排序时间：" + (e - s));
			map.get("selectSort").put(i, (e - s));
			//print(result);
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			Arrays.sort(result);
			e = System.currentTimeMillis();
			System.out.println("JDK Arrays.sort 排序时间：" + (e - s));
			map.get("ArraysSort").put(i, (e - s));
			//print(result);

			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			result = mergeSort(result);
			e = System.currentTimeMillis();
			System.out.println("归并排序1时间：" + (e - s));
			map.get("mergeSort").put(i, (e - s));
			//print(result);

			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			mergeSort2(result, 0, result.length - 1);
			e = System.currentTimeMillis();
			System.out.println("归并排序2时间：" + (e - s));
			map.get("mergeSort2").put(i, (e - s));
			//print(result);
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			quickSort(result, 0, result.length - 1);
			e = System.currentTimeMillis();
			System.out.println("快速排序1时间：" + (e - s));
			map.get("quickSort").put(i, (e - s));
			//print(result);
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			quickSort2(result, 0, result.length - 1);
			e = System.currentTimeMillis();
			System.out.println("快速排序2时间：" + (e - s));
			map.get("quickSort2").put(i, (e - s));
			//print(result);
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			quickSort3(result, 0, result.length - 1);
			e = System.currentTimeMillis();
			System.out.println("快速排序3时间：" + (e - s));
			map.get("quickSort3").put(i, (e - s));
			//print(result);
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			heapSort(result);
			e = System.currentTimeMillis();
			System.out.println("堆排序时间：" + (e - s));
			map.get("heapSort").put(i, (e - s));
			//print(result);
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			shellSort(result);
			e = System.currentTimeMillis();
			System.out.println("希尔排序1时间：" + (e - s));
			map.get("shellSort").put(i, (e - s));
			//print(result);
			
			System.arraycopy(source, 0, result, 0, source.length);
			s = System.currentTimeMillis();
			shellSort2(result);
			e = System.currentTimeMillis();
			System.out.println("希尔排序2时间：" + (e - s));
			map.get("shellSort2").put(i, (e - s));
			//print(result);
		}
		
		printResult(map);

	}
	
	/***          
	 *** @illustration: 初始化              
	 *** @Description : Description                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:22:57
	 *** @version     : v1.0.x                  
	 ***/
	public static void init(Map<String, Map<Integer, Long>> map){
		Map<Integer, Long> timeMap1 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap2 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap3 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap4 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap5 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap6 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap7 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap8 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap9 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap10 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap11 = new LinkedHashMap<Integer, Long>();
		Map<Integer, Long> timeMap12 = new LinkedHashMap<Integer, Long>();
		map.put("insertSort", timeMap1);
		map.put("bubbleSort", timeMap2);
		map.put("selectSort", timeMap3);
		map.put("ArraysSort", timeMap4);
		map.put("mergeSort", timeMap5);
		map.put("mergeSort2", timeMap6);
		map.put("quickSort", timeMap7);
		map.put("quickSort2", timeMap8);
		map.put("quickSort3", timeMap9);
		map.put("heapSort", timeMap10);
		map.put("shellSort", timeMap11);
		map.put("shellSort2", timeMap12);
	}
	
	/***          
	 *** @illustration: 打印输出时间              
	 *** @Description : Description                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:22:57
	 *** @version     : v1.0.x                  
	 ***/
	public static void printResult(Map<String, Map<Integer, Long>> map){
		
		for(Entry<String, Map<Integer, Long>> entry : map.entrySet()){
			Map<Integer, Long> value = entry.getValue();
			System.out.print("\t\t");
			for(Entry<Integer, Long> time : value.entrySet()){
				Integer num = time.getKey();
				System.out.print(num + "\t");
			}
			System.out.println();
			System.out.println();
			break;
		}
		for(Entry<String, Map<Integer, Long>> entry : map.entrySet()){
			String key = entry.getKey();
			Map<Integer, Long> value = entry.getValue();
			System.out.print(key + "\t");
			for(Entry<Integer, Long> time : value.entrySet()){
				Long timeVal = time.getValue();
				System.out.print(timeVal + "ms\t");
			}
			System.out.println();
		}
	}
	
	/***          
	 *** @illustration: 输出数组内容              
	 *** @Description : Description                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:22:57
	 *** @version     : v1.0.x                  
	 ***/
	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	
	/***          
	 *** @illustration:	交换数组arr中i、j位置的值               
	 *** @Description : Description                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:23:35
	 *** @version     : v1.0.x                  
	 ***/
	public static void swap(int[] arr, int i, int j) {
		if (i != j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
	
	/***          
	 *** @illustration: 选择排序              
	 *** @Description : 时间复杂度(平均):O(n^2)	     时间复杂度(最坏):O(n^2)   时间复杂度(最好):O(n^2) 	 空间复杂度:O(1)	稳定性:不稳定 
	 ***				每一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，直到全部待排序的数据元素排完                 
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12下午3:02:28
	 *** @version     : v1.0.x                  
	 ***/
	public static void selectSort(int[] a) {
		int len = a.length;
		for (int i = 0; i < len - 1; i++) {
			int min = i;
			for (int j = i + 1; j < len; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			swap(a, min, i);
		}
	}
	
	/***          
	 *** @illustration: 归并排序              
	 *** @Description : 时间复杂度(平均):O(nlogn)	     时间复杂度(最坏):O(nlogn)   时间复杂度(最好):O(nlogn) 	 空间复杂度:O(n)	稳定性:稳定 
	 ***				该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
	 ***				将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。                 
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:25:02
	 *** @version     : v1.0.x                  
	 ***/
	public static int[] mergeSort(int[] a) {

		if (a.length == 1) {
			return a;
		}
		int[] aLeft = new int[a.length / 2];
		int[] aRight = new int[a.length % 2 == 1 ? (a.length / 2 + 1) : a.length / 2];
		for (int i = 0; i < aLeft.length; i++) {
			aLeft[i] = a[i];
		}
		for (int i = 0; i < aRight.length; i++) {
			aRight[i] = a[a.length - aRight.length + i];
		}
		int[] aLeftRes = mergeSort(aLeft);
		int[] aRigthRes = mergeSort(aRight);
		int[] res = merge(aLeftRes, aRigthRes);
		return res;
	}
	
	/***          
	 *** @illustration: 将两个有序的数组合成一个有序的数组             
	 *** @Description : Description                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:26:25
	 *** @version     : v1.0.x                  
	 ***/
	private static int[] merge(int[] aLeftRes, int[] aRigthRes) {
		int[] a = new int[aLeftRes.length + aRigthRes.length];
		int llen = aLeftRes.length;
		int rlen = aRigthRes.length;
		int index = 0;
		int lindex = 0;
		int rindex = 0;
		while (lindex < llen && rindex < rlen) {
			if (aLeftRes[lindex] < aRigthRes[rindex]) {
				a[index++] = aLeftRes[lindex++];
			} else if (aLeftRes[lindex] == aRigthRes[rindex]) {
				a[index++] = aLeftRes[lindex++];
				a[index++] = aRigthRes[rindex++];
			} else if (aLeftRes[lindex] > aRigthRes[rindex]) {
				a[index++] = aRigthRes[rindex++];
			}
		}
		while (lindex < llen) {
			a[index++] = aLeftRes[lindex++];
		}
		while (rindex < rlen) {
			a[index++] = aRigthRes[rindex++];
		}
		return a;
	}
	
	/***          
	 *** @illustration: 归并排序              
	 *** @Description : 时间复杂度(平均):O(nlogn)	     时间复杂度(最坏):O(nlogn)   时间复杂度(最好):O(nlogn) 	 空间复杂度:O(n)	稳定性:稳定 
	 ***				该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
	 ***				将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。                 
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:25:02
	 *** @version     : v1.0.x                  
	 ***/
	public static int[] mergeSort2(int[] arr, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			mergeSort2(arr, low, mid);
			mergeSort2(arr, mid + 1, high);
			merge2(arr, low, mid, high);
		}
		return arr;
	}
	
	/***          
	 *** @illustration: 将有序数组arr[low...mid] 与  arr[mid+1...high]合成一个有序数组 arr[low...high]              
	 *** @Description : Description                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:30:38
	 *** @version     : v1.0.x                  
	 ***/
	public static void merge2(int[] arr, int low, int mid, int high) {
		int[] a = new int[high - low + 1];
		int index = 0;
		int lindex = low;
		int rindex = mid + 1;
		while (lindex <= mid && rindex <= high) {
			if (arr[lindex] < arr[rindex]) {
				a[index++] = arr[lindex++];
			} else if (arr[lindex] == arr[rindex]) {
				a[index++] = arr[lindex++];
				a[index++] = arr[rindex++];
			} else if (arr[lindex] > arr[rindex]) {
				a[index++] = arr[rindex++];
			}
		}
		while (lindex <= mid) {
			a[index++] = arr[lindex++];
		}
		while (rindex <= high) {
			a[index++] = arr[rindex++];
		}
		for (int i = 0; i < a.length; i++) {
			arr[i + low] = a[i];
		}
	}
	
	/***          
	 *** @illustration: 分隔函数，将大于轴的数全部交换到轴的右边，小于轴的数全部交换到轴的左边，并返回轴的位置(每次以high位置为轴)              
	 *** @Description : Description                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:32:22
	 *** @version     : v1.0.x                  
	 ***/
	public static int partition(int[] arr, int low, int high) {
		int base = arr[high];
		while (low < high) {
			while (low < high && arr[low] <= base) {
				low++;
			}
			if (low < high) {
				swap(arr, low, high);
				high--;
			}
			while (low < high && arr[high] >= base) {
				high--;
			}
			if (low < high) {
				swap(arr, low, high);
				low++;
			}
		}
		// print(arr);
		return high;
	}
	
	/***          
	 *** @illustration: 快速排序              
	 *** @Description : 时间复杂度(平均):O(nlogn)	     时间复杂度(最坏):O(n^2)   时间复杂度(最好):O(nlogn) 	 空间复杂度:O(nlogn)	稳定性:不稳定
	 ***				通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
	 ***				然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:55:03
	 *** @version     : v1.0.x                  
	 ***/
	public static void quickSort(int arr[], int low, int high) {
		if (low >= high)
			return;
		int partition = partition(arr, low, high);
		quickSort(arr, low, partition - 1);
		quickSort(arr, partition + 1, high);
	}
	
	/***          
	 *** @illustration: 分隔函数，将大于轴的数全部交换到轴的右边，小于轴的数全部交换到轴的左边，并返回轴的位置(每次以high位置为轴)              
	 *** @Description : Description                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:32:22
	 *** @version     : v1.0.x                  
	 ***/
	public static int partition2(int[] arr, int low, int high) {
		int base = arr[low];
		while (low < high) {
			while (low < high && arr[high] >= base) {
				high--;
			}
			if (low < high) {
				swap(arr, low, high);
				low++;
			}
			while (low < high && arr[low] <= base) {
				low++;
			}
			if (low < high) {
				swap(arr, low, high);
				high--;
			}
		}
		// print(arr);
		return high;
	}
	
	/***          
	 *** @illustration: 快速排序              
	 *** @Description : 时间复杂度(平均):O(nlogn)	     时间复杂度(最坏):O(n^2)   时间复杂度(最好):O(nlogn) 	 空间复杂度:O(nlogn)	稳定性:不稳定
	 ***				通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
	 ***				然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:55:03
	 *** @version     : v1.0.x                  
	 ***/
	public static void quickSort2(int arr[], int low, int high) {
		if (low >= high)
			return;
		int partition = partition2(arr, low, high);
		quickSort2(arr, low, partition - 1);
		quickSort2(arr, partition + 1, high);
	}
	
	/***          
	 *** @illustration: 快速排序              
	 *** @Description : 时间复杂度(平均):O(nlogn)	     时间复杂度(最坏):O(n^2)   时间复杂度(最好):O(nlogn) 	 空间复杂度:O(nlogn)	稳定性:不稳定
	 ***				通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
	 ***				然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:55:03
	 *** @version     : v1.0.x                  
	 ***/
	public static void quickSort3(int arr[], int low, int high) {
		if (low > high) {
			return;
		}
		int base = arr[low];
		int i = low;
		int j = high;
		while (i < j) {
			while (arr[j] >= base && i < j)
				j--;
			while (arr[i] <= base && i < j)
				i++;
			if (i < j)
				swap(arr, i, j);
		}
		arr[low] = arr[i];
		arr[i] = base;
		quickSort3(arr, low, i - 1);
		quickSort3(arr, i + 1, high);
	}
	
	/***          
	 *** @illustration: 堆调整，将a数组中的 第 i 个元素进行堆调整(大顶堆)              
	 *** @Description : Description                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:34:05
	 *** @version     : v1.0.x                  
	 ***/
	public static void heapAdjust(int[] a, int len, int i) {
		int max = i;
		int curr = i;
		while (max < len) {
			int lchild = (curr << 1) + 1;
			int rchild = (curr << 1) + 2;
			// System.out.println(curr + " " + lchild +" " + rchild);
			if (lchild < len && a[max] < a[lchild]) {
				max = lchild;
			}
			if (rchild < len && a[max] < a[rchild]) {
				max = rchild;
			}
			if (curr == max) {
				break;
			}
			swap(a, max, curr);
			curr = max;
		}
	}
	
	/***          
	 *** @illustration: 堆排序              
	 *** @Description :	时间复杂度(平均):O(nlogn)	     时间复杂度(最坏):O(nlogn)   时间复杂度(最好):O(nlogn) 	 空间复杂度:O(1)	稳定性:不稳定 
	 ***				初始时把要排序的数的序列看作是一棵顺序存储的二叉树，调整它们的存储序，使之成为一个堆，这时堆的根节点的数最大。
	 ***				然后将根节点与堆的最后一个节点交换，对前面(n-1)个数重新调整使之成为堆；
	 ***				依此类推，直到只有两个节点的堆，并对它们作交换，最后得到有n个节点的有序序列。
	 ***				从算法描述来看，堆排序需要两个过程，一是建立堆，二是堆顶与堆的最后一个元素交换位置。
	 ***				所以堆排序有两个函数组成。一是建堆的调整函数，二是反复调用调整函数实现排序的函数。                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:35:24
	 *** @version     : v1.0.x                  
	 ***/
	public static void heapSort(int[] a) {
		int len = a.length;
		// 将数组a转化为大顶堆
		for (int i = len / 2; i >= 0; i--) {
			heapAdjust(a, len, i);
		}
		swap(a, 0, len - 1);
		for (int i = 1; i < len; i++) {
			heapAdjust(a, len - i, 0);
			swap(a, 0, len - i - 1);
		}
	}
	
	/***          
	 *** @illustration: 冒泡排序              
	 *** @Description : 时间复杂度(平均):O(n^2)	     时间复杂度(最坏):O(n^2)   时间复杂度(最好):O(n) 	 空间复杂度:O(1)	稳定性:稳定
	 ***				冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
	 ***				走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:18:17
	 *** @version     : v1.0.x                  
	 ***/
	public static void bubbleSort(int[] a) {
		int len = a.length;
		for (int i = 0; i < len - 1; i++) {
			boolean flag = true;
			for (int j = 0; j < len - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
					flag = false;
				}
			}
			if(flag){
				break;
			}
		}
	}
	
	/***          
	 *** @illustration: 插入排序              
	 *** @Description :	时间复杂度(平均):O(n^2)	     时间复杂度(最坏):O(n^2)   时间复杂度(最好):O(n) 	 空间复杂度:O(1)	稳定性:稳定	
	 ***				每步将一个待排序的记录，按其关键码值的大小插入前面已经排序的文件中适当位置上，直到全部插入完为止。                  
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:15:19
	 *** @version     : v1.0.x                  
	 ***/
	public static void insertSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int j = i;
			int temp = a[i];
			while(j > 0 && a[j - 1] > temp){
				a[j] =a[j-1]; 
				j--;
			}
			a[j] = temp;
		}
	}
	
	/***          
	 *** @illustration: 希尔排序            
	 *** @Description : 分组插入方法，步长逐渐减半，直到为1	(n/2 n/4 n/8 ... n/(2^n) ... 1)	
	 ***				时间复杂度(平均):O(n^1.3)	时间复杂度(最坏):O(n^2)   时间复杂度(最好):O(n) 	空间复杂度:O(1)	稳定性:不稳定	
	 ***				希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
	 ***				随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止 。            
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午9:11:40
	 *** @version     : v1.0.x                  
	 ***/
	public static void shellSort(int[] a){
		int len = a.length;
		for(int i = len / 2; i > 0; i = i/2){
			for(int j = i; j < len; j++){
				int temp = a[j];
				int index = j;
				while(index - i >= 0 && a[index - i] > temp){
					a[index] = a[index - i];
					index = index - i;
				}
				a[index] = temp;
			}
		}
	}
	
	/***          
	 *** @illustration: 希尔排序              
	 *** @Description : 分组插入方法，步长逐渐缩小3倍，直到1  (1,4,13,40,121 ...)
	 ***				时间复杂度(平均):O(n^1.3)	时间复杂度(最坏):O(n^2)   时间复杂度(最好):O(n) 	空间复杂度:O(1)	稳定性:不稳定	
	 ***				希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
	 ***				随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。                 
	 *** @author      : 程传强                   
	 *** @date        : 2018-4-12上午10:00:50
	 *** @version     : v1.0.x                  
	 ***/
	public static void shellSort2(int[] a) {
		int len = a.length;
		int h = 1;
		while (h < len / 3) {
			h = h * 3 + 1;
		}
		while (h >= 1) {
			for (int i = h; i < len; i++) {
				int temp = a[i];
				int index = i;
				while (index - h >= 0 && a[index - h] > temp) {
					a[index] = a[index - h];
					index = index - h;
				}
				a[index] = temp;
			}
			h = h / 3;
		}
	}
	
}
