package algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	
	static final int MaxNum = 20;  					// 图的最大定点数
	static final int MaxVal = 65535;				// 最大值
	
	char[] vertex = new char[MaxNum];				// 保存定点信息
	int vertexNum;									// 定点数量
	int edgeNum;									// 边的数量
	int[][] edgeWeight = new int[MaxNum][MaxNum];	// 保存边的权
	int[] isTrav = new int[MaxNum];					// 遍历标志
	
	// 初始化图
	public void createGraph() {
		vertex[0] = 'A';
		vertex[1] = 'B';
		vertex[2] = 'C';
		vertex[3] = 'D';
		
		vertexNum = 4;
		edgeNum = 6;
		for(int i = 0; i < vertexNum; i++){
			for(int j = 0; j < vertexNum; j++){
				edgeWeight[i][j] = Graph.MaxVal;
			}
		}
		edgeWeight[0][1] = 1;
		edgeWeight[1][0] = 1;
		edgeWeight[0][2] = 1;
		edgeWeight[2][0] = 1;
		edgeWeight[0][3] = 1;
		edgeWeight[3][0] = 1;
		
		edgeWeight[1][2] = 2;
		edgeWeight[2][1] = 2;
		edgeWeight[1][3] = 2;
		edgeWeight[3][1] = 2;
	}
	
	// 显示图矩阵
	public void outGraph(){
		for(int i = 0; i < vertexNum; i++){
			System.out.print("\t" + vertex[i]);
		}
		System.out.println();
		
		for(int i = 0; i < vertexNum; i++){
			System.out.print(vertex[i] + "\t");
			for(int j = 0; j < vertexNum; j++){
				
				if(edgeWeight[i][j] == Graph.MaxVal){
					System.out.print("Z\t");
				}else{
					System.out.print(edgeWeight[i][j] + "\t");
				}
			}
			System.out.println();
		}
	}
	
	// 递归深度优先遍历
	public void DFS_Graph(Graph graph) {
		for (int i = 0; i < vertexNum; i++) {
			isTrav[i] = 0;
		}
		System.out.println("递归深度优先遍历");
		for (int i = 0; i < vertexNum; i++) {
			if (isTrav[i] == 0) {
				DFS(graph, i);
			}
		}
		System.out.println();
	}
	
	public void DFS(Graph graph, int n) {
		isTrav[n] = 1;
		System.out.print("->" + graph.vertex[n]);
		for (int i = 0; i < vertexNum; i++) {
			if (isTrav[i] == 0 && graph.edgeWeight[n][i] != Graph.MaxVal) {
				DFS(graph, i);
			}
		}
	}
	
	// 非递归深度优先遍历
	public List<Integer> DFS_Graph2(int[][] graph) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < vertexNum; i++) {
			isTrav[i] = 0;
		}
		System.out.println("非递归深度优先遍历");
		List<Integer> list = new ArrayList<>();
		list.add(0);
		isTrav[0] = 1;
		stack.push(0);
		while (!stack.isEmpty()) {
			int i = stack.peek();
			int j = 0;
			for(; j < vertexNum; j++){
				if(graph[i][j] != Graph.MaxVal && isTrav[j] == 0){
					break;
				}
			}
			if(j < vertexNum){
				isTrav[j] = 1;
				list.add(j);
				stack.push(j);
			}else{
				stack.pop();
			}
		}
		
		for(int i =0;i<list.size();i++){
			System.out.print("->" + vertex[i]);
		}
		System.out.println();
		return list;
	}
	
	// 广度优先遍历
	public void BFS(int[][] graph) {
		System.out.println("非递归广度优先遍历");
		Queue<Integer> queue = new ArrayDeque<>();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vertexNum; i++) {
			isTrav[i] = 0;
		}
		isTrav[0] = 1;
		queue.add(0);
		list.add(0);
		while (!queue.isEmpty()) {
			Integer i = queue.poll();
			for(int j = 0; j < vertexNum; j++){
				if(graph[i][j] != Graph.MaxVal && isTrav[j] == 0){
					isTrav[j] = 1;
					list.add(j);
					queue.add(j);
				}
			}
		}
		
		for(int i =0;i<list.size();i++){
			System.out.print("->" + vertex[i]);
		}
		System.out.println();
	}	
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.createGraph();
		graph.outGraph();
		graph.DFS_Graph(graph);
		graph.DFS_Graph2(graph.edgeWeight);
		graph.BFS(graph.edgeWeight);
	}

}
