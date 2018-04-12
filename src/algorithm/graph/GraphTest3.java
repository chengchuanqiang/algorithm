package algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @ClassName: GraphTest3.java
 * @Description: YTUOJ 3078: 数据结构：图--列出连通集
 * @author: ccq
 * @date: 2018年3月13日 下午9:29:37
 *
 */
public class GraphTest3 {
	
	public static final int MAXVAL = Integer.MAX_VALUE; 
	public static int vis[];
	public static int n, edge;
	public static int map[][];
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()){
			n = input.nextInt();
			edge = input.nextInt();
			map = new int[n][n];
			
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n ;j++){
					map[i][j] = MAXVAL;
				}
			}
			int a,b;
			for(int i = 0; i < edge; i++){
				a = input.nextInt();
				b = input.nextInt();
				map[a][b] = 1;
				map[b][a] = 1;
			}
			vis = new int[n];
			for(int i = 0; i < n ;i++){
				vis[i] = 0;
			}
			for(int i = 0; i < n; i++){
				if(vis[i] == 0){
					DFS(map, i);
				}
			}
			
			for(int i = 0; i < n ;i++){
				vis[i] = 0;
			}
			for(int i = 0; i < n; i++){
				if(vis[i] == 0){
					BFS(map, i);
				}
			}
			
		}
		
		input.close();
	}
	
	// 深度优先遍历
	public static void DFS(int[][] map, int m) {
		Stack<Integer> stack = new Stack<>();
		List<Integer> list = new ArrayList<>();
		stack.add(m);
		list.add(m);
		vis[m] = 1;
		while (!stack.isEmpty()) {
			int i = stack.peek();
			int j = 0;
			for (; j < n; j++) {
				if (map[i][j] != MAXVAL && vis[j] == 0) {
					break;
				}
			}
			if (j < n) {
				list.add(j);
				vis[j] = 1;
				stack.add(j);
			} else {
				stack.pop();
			}
		}
		System.out.print("{ ");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("}");
	}

	// 广度优先遍历
	public static void BFS(int[][] map, int m) {
		Queue<Integer> queue = new ArrayDeque<>();
		List<Integer> list = new ArrayList<>();
		
		queue.add(m);
		vis[m] = 1;
		list.add(m);
		
		while(!queue.isEmpty()){
			int i = queue.poll();
			for(int j = 0; j < n; j++){
				if(map[i][j] != MAXVAL && vis[j] == 0){
					queue.add(j);
					list.add(j);
					vis[j] = 1;
				}
			}
		}
		System.out.print("{ ");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println("}");
	}
}
