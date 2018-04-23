package algorithm.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @ClassName: SortTest1.java
 * @Description: 郑州轻工业ACM 2269: minval
 * @author: ccq
 * @date: 2018年4月23日 下午10:05:32
 *
 */
public class SortTest1 {

	/**
	 * 题目分析：显而易见，这个题目是一个卡时间的题目 
	 * 第一种方案： 
	 * （1）首先将A，B两个数组进行排序，然后将A[0]+B[i]求和放到num[]数组中，且num[]数组是有序的
	 * （2）然后进行两层for循环遍历，将a[i]+b[j] < num[n-1] 插入到num[] 数组中，并且保持有序
	 * 
	 * 虽然在循环中进行优化，对于复杂的数据，代码的时间复杂度应该接近O(N^3)
	 * 为啥？两层for循环O(N^2),虽然二分查找的时间复杂度是O(logN),但是数值插入的时间是O(N)的，所以最后的时间复杂度O(N^3)
	 */

	public static void main(String[] args) {
		int[] num;// 目标数组
		int[] a;  // A数组
		int[] b;  // B数组
		int n;	  // 数组A、B的长度
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			n = input.nextInt();
			num = new int[n];
			a = new int[n];
			b = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = input.nextInt();
			}
			for (int i = 0; i < n; i++) {
				b[i] = input.nextInt();

			}
			// 分别对A、B进行排序
			Arrays.sort(a);
			Arrays.sort(b);
			
			// 初始化有序数组num[]
			for (int i = 0; i < n; i++) {
				num[i] = a[0] + b[i];
			}
			
			// 两层for循环
			for (int i = 1; i < n; i++) {
				// 如果a[i]+b[0] >= num[n-1]的时候说明以后所有a[i]+b[j]一定是大于num[n-1]，
				// num[n-1] 是此时数组最大的数，所以没有必要在进行遍历，直接break即可
				if (a[i] + b[0] >= num[n - 1]) {
					break;
				}
				for (int j = 0; j < n; j++) {
					if (a[i] + b[j] >= num[n - 1]) {
						break;
					}
					// 使用二分查找查询离a[i]+b[j]最近的值的位置
					int index = search(num, a[i] + b[j], 0, n - 1);
					// 将a[i]+b[j] 插入到num[] 数组中，同时保持有序
					insertVal(num, a[i] + b[j], index, n - 1);
				}
			}
			printNum(num, n);
		}
		input.close();
	}
	
	/**
	 * 二分查找
	 * @param num	目标数组
	 * @param v		二分查找的值
	 * @param low	
	 * @param high
	 * @return
	 */
	public static int search(int[] num, int v, int low, int high) {
		while (low <= high) {
			int mid = (low + high) / 2;
			if (v == num[mid]) {
				return mid;
			} else if (v > num[mid]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return high;
	}
	
	/**
	 * 将X值插入到index+1的位置，从数组的最后n-1的位置移动
	 * @param num	目标数组
	 * @param x		插入的值
	 * @param index	目标位置
	 * @param k		数组指针
	 */
	public static void insertVal(int[] num, int x, int index, int k) {
		for (; k > index + 1; k--) {
			num[k] = num[k - 1];
		}
		num[k] = x;
	}
	/**
	 * 打印输出
	 * @param num
	 * @param n
	 */
	public static void printNum(int[] num, int n) {
		System.out.print(num[0]);
		for (int i = 1; i < n; i++) {
			System.out.print(" " + num[i]);
		}
		System.out.println();
	}
}
