package algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @ClassName: SortTest1.java
 * @Description: 郑州轻工业ACM 2269: minval
 * @author: ccq
 * @date: 2018年4月23日 下午10:05:32
 *
 */
public class SortTest3 {

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
	 * 哈哈,顺利水过
	 * 
	 * 当然，java的api还是很强大的，JDK中优先队列对堆进行了封装实现，也就是：PriorityQueue 这个玩意，我试试效果咋样
	 */

	public static void main(String[] args) {
		int[] num;// 目标数组
		int[] a; // A数组
		int[] b; // B数组
		int n; // 数组A、B的长度
		Queue<Integer> queue;
		Scanner input = new Scanner(System.in);
		while (input.hasNext()) {
			n = input.nextInt();
			num = new int[n];
			a = new int[n];
			b = new int[n];
			
			// 自定义的比较器，可以让我们自由定义比较的顺序  Comparator<Integer> cmp;
	        // 生成最大堆使用e2-e1,生成最小堆使用e1-e2,
			queue = new PriorityQueue<Integer>(n,new Comparator<Integer>() {
				@Override
				public int compare(Integer e1, Integer e2) {
					return e2 - e1; 
				}
			});
			for (int i = 0; i < n; i++) {
				a[i] = input.nextInt();
			}
			for (int i = 0; i < n; i++) {
				b[i] = input.nextInt();

			}
			// 分别对A、B进行排序
			Arrays.sort(a);
			Arrays.sort(b);

			for (int i = 0; i < n; i++) {
				queue.add(a[0] + b[i]);
			}

			// 两层for循环
			for (int i = 1; i < n; i++) {
				// 如果a[i]+b[0] >= num[n-1]的时候说明以后所有a[i]+b[j]一定是大于num[n-1]，
				// num[n-1] 是此时数组最大的数，所以没有必要在进行遍历，直接break即可
				if (a[i] + b[0] >= queue.peek()) {
					break;
				}
				for (int j = 0; j < n; j++) {
					if (a[i] + b[j] >= queue.peek()) {
						break;
					}
					queue.poll();
                    queue.add(a[i] + b[j]);
				}
			}
			for (int i = n - 1; i >= 0; i--) {
                num[i] = queue.poll();
            }
			printNum(num, n);
		}
		input.close();
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
