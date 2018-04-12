package algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * YTUOJ 2345: 后序遍历二叉树
 * @author ccq
 *
 */
public class TreeTest3 {
	
	public final static int MAX = 1005; 
	
	public static void main(String[] args) {
		int count = 0;
		List<String> arr = null;
		Scanner input = new Scanner(System.in);
		count = input.nextInt();
		while((count--) > 0) {
			arr = new ArrayList<String>();
			while(true) {
				int temp = input.nextInt();
				if(temp == -1) break;
				arr.add(String.valueOf(temp));
			}
			
			TreeNode tree = initTree(arr);
			int depth = getTreeDepth(tree);
			System.out.print(depth);
			LRDTree(tree);
			System.out.println();
		}
		input.close();
	}
	
	public static void LRDTree(TreeNode rootNode) {
		if(rootNode != null) {
			LRDTree(rootNode.left);
			LRDTree(rootNode.right);
			System.out.print(" "+rootNode.data);
		}
	}
	
	public static TreeNode initTree(List<String> arr) {
		
		if(arr.size() == 1) {
			return new TreeNode(arr.get(0),null,null);
		}
		
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		for(int i = 0; i < arr.size(); i++) {
			nodeList.add(new TreeNode(arr.get(i), null, null));
		}
		int index = 0;
		while(index < arr.size() / 2) {
			
			if(nodeList.get(index).data.equals("0")) {
				continue;
			}
			if(index * 2 + 1 < arr.size() && !nodeList.get(index * 2 + 1).data.equals("0")) {
				nodeList.get(index).left = nodeList.get(index * 2 + 1);
			}
			if(index * 2 + 2 < arr.size() && !nodeList.get(index * 2 + 2).data.equals("0")) {
				nodeList.get(index).right = nodeList.get(index * 2 + 2);
			}
			index++;
		}
		
		return nodeList.get(0);
	}
	
	public static int getTreeDepth(TreeNode rootNode) {
		if(rootNode == null) {
			return 0;
		}
		int leftDepth = getTreeDepth(rootNode.left);
		int rigthDepth = getTreeDepth(rootNode.right);
		return leftDepth >= rigthDepth ? leftDepth + 1 : rigthDepth + 1;
	}
}


//class TreeNode{
//	public String data;
//	public TreeNode left;
//	public TreeNode right;
//	
//	public TreeNode() {}
//	
//	public TreeNode(String data, TreeNode left, TreeNode right) {
//		super();
//		this.data = data;
//		this.left = left;
//		this.right = right;
//	}
//	
//}