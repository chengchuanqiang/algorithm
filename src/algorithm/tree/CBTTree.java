package algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 完全二叉树
 * 
 * @author ccq
 *
 */
public class CBTTree {

	/**
	 * 初始化二叉树
	 * @param arr
	 * @return 树根节点
	 */
	public static CBTNode initTree(String[] arr) {
		if (arr == null || arr.length == 0) {
			return new CBTNode();
		}
		// 只有根结点
		if (arr.length == 1) {
			return new CBTNode(arr[0], null, null);
		}

		// 初始化所有的结点
		List<CBTNode> nodeList = new ArrayList<CBTNode>();
		for (int i = 0; i < arr.length; i++) {
			nodeList.add(new CBTNode(arr[i], null, null));
		}

		// 初始化结点之间关系
		int index = 0;
		while (index < arr.length / 2) { // 树去掉最后一层的结点，剩余节点为m/2个
			if (2 * index + 1 < arr.length) {
				nodeList.get(index).left = nodeList.get(2 * index + 1);
			}
			if (2 * index + 2 < arr.length) {
				nodeList.get(index).right = nodeList.get(2 * index + 2);
			}
			index++;
		}
		System.out.println();
		System.out.println("*********************");
		System.out.println("***** 树初始化完成  *****");
		System.out.println("*********************");
		System.out.println();
		return nodeList.get(0);
	}

	/**
	 * 计算树的深度
	 * 
	 * @return
	 */
	public static int treeDepth(CBTNode rootNode) {
		if (rootNode == null) {
			return 0;
		}

		int leftDepth = treeDepth(rootNode.left);
		int rightDepth = treeDepth(rootNode.right);

		if (leftDepth > rightDepth) {
			return leftDepth + 1;
		} else {
			return rightDepth + 1;
		}

	}

	/**
	 * 层次遍历
	 * 
	 * @param rootNode
	 */
	public static void levelTree(CBTNode rootNode) {
		Queue<CBTNode> nodeQueue = new ArrayDeque<>();
		nodeQueue.add(rootNode);
		CBTNode node = null;
		while ((node = nodeQueue.poll()) != null) {
			if (node.left != null) {
				nodeQueue.add(node.left);
			}
			if (node.right != null) {
				nodeQueue.add(node.right);
			}
			System.out.print(node.data + " ");
		}
	}

	/**
	 * 先序遍历
	 * 
	 * @param rootNode
	 */
	public static void DLRTree(CBTNode rootNode) {
		if (rootNode != null) {
			System.out.print(rootNode.data + " ");
			DLRTree(rootNode.left);
			DLRTree(rootNode.right);
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @param rootNode
	 */
	public static void LDRTree(CBTNode rootNode) {
		if (rootNode != null) {
			LDRTree(rootNode.left);
			System.out.print(rootNode.data + " ");
			LDRTree(rootNode.right);
		}
	}

	/**
	 * 后序遍历
	 * 
	 * @param rootNode
	 */
	public static void LRDTree(CBTNode rootNode) {
		if (rootNode != null) {
			LRDTree(rootNode.left);
			LRDTree(rootNode.right);
			System.out.print(rootNode.data + " ");
		}
	}

	/**
	 * 非递归先序遍历
	 * 
	 * @param rootNode
	 */
	public static void DLRTree_stack(CBTNode rootNode) {
		Stack<CBTNode> stack = new Stack<>();
		CBTNode node = rootNode;
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				System.out.print(node.data + " ");
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				node = node.right;
			}
		}
	}

	/**
	 * 非递归中序遍历
	 * 
	 * @param rootNode
	 */
	public static void LDRTree_stack(CBTNode rootNode) {
		Stack<CBTNode> stack = new Stack<>();
		CBTNode node = rootNode;
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				System.out.print(node.data + " ");
				node = node.right;
			}
		}
	}

	/**
	 * 非递归后序遍历
	 * 
	 * @param rootNode
	 */
	public static void LRDTree_stack(CBTNode rootNode) {
		// 使用输出栈进行遍历
		Stack<CBTNode> stack = new Stack<>();
		Stack<CBTNode> output = new Stack<>();
		CBTNode node = rootNode;
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				output.push(node);
				stack.push(node);
				node = node.right;
			} else {
				node = stack.pop();
				node = node.left;
			}
		}
		while (!output.isEmpty()) {
			System.out.print(output.pop().data + " ");
		}
		System.out.println();
		
		// 记录当前结点的前结点方式进行后续遍历
		System.out.print("LRDTree1 is ");
		Stack<CBTNode> stack2 = new Stack<>();
		CBTNode cur = null;
		CBTNode pre = null;
		stack2.push(rootNode);
		while (!stack2.isEmpty()) {
			cur = stack2.peek();
			if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
				System.out.print(cur.data + " ");
				stack2.pop();
				pre = cur;
			} else {
				if (cur.right != null) {
					stack2.push(cur.right);
				}
				if (cur.left != null) {
					stack2.push(cur.left);
				}
			}
		}

	}
	
	/**
	 * 树结点递归查找
	 * @param node
	 * @param data
	 * @return
	 */
	public static CBTNode searchNode(CBTNode node,String data) {
		
		CBTNode treeNode;
		
		if(node == null) {
			return null;
		}
		
		if(node.data.equals(data)) {
			return node;
		}else {
			treeNode = searchNode(node.left, data);
			if(treeNode != null) {
				return treeNode;
			}
			return searchNode(node.right, data);
		}
	}
	
	/**
	 * 显示栈查找
	 * @param node
	 * @param data
	 * @return
	 */
	public static CBTNode searchNode_stack(CBTNode node,String data) {
		CBTNode treeNode = null;
		Stack<CBTNode> stack = new Stack<CBTNode>();
		if(node == null) {
			return null;
		}
		
		stack.push(node);
		while(!stack.isEmpty()) {
			treeNode = stack.pop();
			if(treeNode.data.equals(data)) {
				return treeNode;
			}
			if(treeNode.left != null) {
				stack.push(treeNode.left);
			}
			if(treeNode.right != null) {
				stack.push(treeNode.right);
			}
		}
		
		return null;
	}

	public static void main(String[] args) {
		// 构建一棵完全二叉树
		String[] arr = { "A", "B", "C", "D", "E", "F" };
		CBTNode tree = initTree(arr);
		
		// 查找结点
		System.out.println("******************************递归查找结点*******************************");
		CBTNode searchNodeA = searchNode(tree,"A");
		System.out.println("查找结点：A : " + ((searchNodeA == null) ? null : searchNodeA.toString()));
		CBTNode searchNodeB = searchNode(tree,"B");
		System.out.println("查找结点：B : " + ((searchNodeB == null) ? null : searchNodeB.toString()));
		CBTNode searchNodeC = searchNode(tree,"C");
		System.out.println("查找结点：C : " + ((searchNodeC == null) ? null : searchNodeC.toString()));
		CBTNode searchNodeD = searchNode(tree,"D");
		System.out.println("查找结点：D : " + ((searchNodeD == null) ? null : searchNodeD.toString()));
		CBTNode searchNodeE = searchNode(tree,"E");
		System.out.println("查找结点：E : " + ((searchNodeE == null) ? null : searchNodeE.toString()));
		CBTNode searchNodeF = searchNode(tree,"F");
		System.out.println("查找结点：F : " + ((searchNodeF == null) ? null : searchNodeF.toString()));
		CBTNode searchNodeG = searchNode(tree,"G");
		System.out.println("查找结点：G : " + ((searchNodeG == null) ? null : searchNodeG.toString()));
		CBTNode searchNodeH = searchNode(tree,"H");
		System.out.println("查找结点：H : " + ((searchNodeH == null) ? null : searchNodeH.toString()));
		System.out.println("******************************递归查找结点*******************************");
		
		System.out.println("******************************非递归查找结点*******************************");
		searchNodeA = searchNode_stack(tree,"A");
		System.out.println("查找结点：A : " + ((searchNodeA == null) ? null : searchNodeA.toString()));
		searchNodeB = searchNode_stack(tree,"B");
		System.out.println("查找结点：B : " + ((searchNodeB == null) ? null : searchNodeB.toString()));
		searchNodeC = searchNode_stack(tree,"C");
		System.out.println("查找结点：C : " + ((searchNodeC == null) ? null : searchNodeC.toString()));
		searchNodeD = searchNode_stack(tree,"D");
		System.out.println("查找结点：D : " + ((searchNodeD == null) ? null : searchNodeD.toString()));
		searchNodeE = searchNode_stack(tree,"E");
		System.out.println("查找结点：E : " + ((searchNodeE == null) ? null : searchNodeE.toString()));
		searchNodeF = searchNode_stack(tree,"F");
		System.out.println("查找结点：F : " + ((searchNodeF == null) ? null : searchNodeF.toString()));
		searchNodeG = searchNode_stack(tree,"G");
		System.out.println("查找结点：G : " + ((searchNodeG == null) ? null : searchNodeG.toString()));
		searchNodeH = searchNode_stack(tree,"H");
		System.out.println("查找结点：H : " + ((searchNodeH == null) ? null : searchNodeH.toString()));
		System.out.println("******************************非递归查找结点*******************************");

		
		// 求树的深度
		int treeDepth = treeDepth(tree);
		System.out.println("tree1 depth is " + treeDepth);

		// 进行层次遍历
		System.out.print("levelTree1 is ");
		levelTree(tree);
		System.out.println();

		System.out.println("**************递归遍历**************");
		// 进行先序遍历
		System.out.print("DLRTree1 is ");
		DLRTree(tree);
		System.out.println();

		// 进行中序遍历
		System.out.print("LDRTree1 is ");
		LDRTree(tree);
		System.out.println();

		// 进行后序遍历
		System.out.print("LRDTree1 is ");
		LRDTree(tree);
		System.out.println();
		System.out.println("**************递归遍历**************");

		System.out.println("**************非递归遍历**************");
		// 进行先序遍历
		System.out.print("DLRTree1 is ");
		DLRTree_stack(tree);
		System.out.println();

		// 进行中序遍历
		System.out.print("LDRTree1 is ");
		LDRTree_stack(tree);
		System.out.println();

		// 进行后序遍历
		System.out.print("LRDTree1 is ");
		LRDTree_stack(tree);
		System.out.println();
		System.out.println("**************非递归遍历**************");

		String[] arr2 = { "A", "B", "C", "D", "E", "F", "G", "H" };
		CBTNode tree2 = initTree(arr2);

		int treeDepth2 = treeDepth(tree2);
		System.out.println("tree2 depth is " + treeDepth2);

		System.out.print("levelTree1 is ");
		levelTree(tree2);
		System.out.println();

		System.out.println("**************递归遍历**************");
		System.out.print("DLRTree2 is ");
		DLRTree(tree2);
		System.out.println();

		System.out.print("LDRTree2 is ");
		LDRTree(tree2);
		System.out.println();

		System.out.print("LRDTree2 is ");
		LRDTree(tree2);
		System.out.println();
		System.out.println("**************递归遍历**************");

		System.out.println("**************非递归遍历**************");
		// 进行先序遍历
		System.out.print("DLRTree2 is ");
		DLRTree_stack(tree2);
		System.out.println();

		// 进行中序遍历
		System.out.print("LDRTree2 is ");
		LDRTree_stack(tree2);
		System.out.println();

		// 进行后序遍历
		System.out.print("LRDTree2 is ");
		LRDTree_stack(tree2);
		System.out.println();
		System.out.println("**************非递归遍历**************");
	}
}

/**
 * 结点数据结构
 * 
 * @author ccq
 *
 */
class CBTNode {
	String data;
	CBTNode left;
	CBTNode right;

	public CBTNode() {
	}

	public CBTNode(String data, CBTNode left, CBTNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "CBTNode [data=" + data + ", left=" + left + ", right=" + right + "]";
	}
	
}
