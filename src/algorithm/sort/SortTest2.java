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
public class SortTest2 {

	/**
	 * 题目分析：显而易见，这个题目是一个卡时间的题目 第一种方案：
	 * （1）首先将A，B两个数组进行排序，然后将A[0]+B[i]求和放到num[]数组中，且num[]数组是有序的
	 * （2）然后进行两层for循环遍历，将a[i]+b[j] < num[n-1] 插入到num[] 数组中，并且保持有序
	 * 
	 * 虽然在循环中进行优化，对于复杂的数据，代码的时间复杂度应该接近O(N^3)
	 * 为啥？两层for循环O(N^2),虽然二分查找的时间复杂度是O(logN),但是数值插入的时间是O(N)的，所以最后的时间复杂度O(N^3)
	 * 
	 * 
	 * 对于第一种方案肯定是超时，果不其然，提交了一下OJ，超时刚刚的！
	 * 分析一下，对于两层for循环是不可能避免的，因为需要计算a[i]+b[j]的值，但是对于num[]的维护可以进行优化的
	 * 如果可以把num[]中插入值的操作优化一下，代码应该是可以过的
	 * 首先想到的是用堆，什么是堆尼？自己百度吧，很简单，对于大根堆的调整时间复杂度是O(logN) 也就是说可以优化到时间复杂度到O(N^2logN)
	 * 
	 * 第二种方案：
	 * （1）首先将A，B两个数组进行排序，然后将A[0]+B[i]求和放到num[]数组中，且num[]数组是有序的，构造成从大到小的数组，同时它还是一个大根堆，好巧啊 
	 * （2）然后进行两层for循环遍历，将a[i]+b[j] <num[0]，使num[0]=a[i]+b[j]，进行大根堆的调整 
	 * （3）最后排序输出 
	 * 美滋滋，这一回肯定可以过
	 * 
	 * 哈哈,顺利水过
	 */

	public static void main(String[] args) {
		int[] num;// 目标数组
		int[] a; // A数组
		int[] b; // B数组
		int n; // 数组A、B的长度
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

			// num 大根堆
			for (int i = 0; i < n; i++) {
				num[n - i - 1] = a[0] + b[i];
			}

			// 两层for循环
			for (int i = 1; i < n; i++) {
				// 如果a[i]+b[0] >= num[n-1]的时候说明以后所有a[i]+b[j]一定是大于num[n-1]，
				// num[n-1] 是此时数组最大的数，所以没有必要在进行遍历，直接break即可
				if (a[i] + b[0] >= num[0]) {
					break;
				}
				for (int j = 0; j < n; j++) {
					if (a[i] + b[j] >= num[0]) {
						break;
					}
					num[0] = a[i] + b[j];
					// 对0位置进行堆调整
					heapAdjust(num, n, 0);
				}
			}
			Arrays.sort(num);
			printNum(num, n);
		}
		input.close();
	}
	
	/**
	 * 将数组a进行大根堆调整
	 * @param a
	 * @param len
	 * @param i
	 */
	public static void heapAdjust(int[] a, int len, int i) {
		int max = i;
		int curr = i;
		while (max < len) {
			int lchild = (curr << 1) + 1;
			int rchild = (curr << 1) + 2;
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

	public static void swap(int[] arr, int i, int j) {
		if (i != j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	/**
	 * 打印输出
	 * 
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
