package algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * YTUOJ 3023: 树的遍历 根据中序和后序构建二叉树，进行层次遍历
 * 
 * @author ccq
 *
 */
public class TreeTest4 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int len = input.nextInt();
		List<String> ldrList = new ArrayList<String>();
		List<String> lrdList = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			lrdList.add(String.valueOf(input.nextInt()));
		}
		for (int i = 0; i < len; i++) {
			ldrList.add(String.valueOf(input.nextInt()));
		}
		TreeNode tree = buildTree(ldrList, lrdList);
		levelTree(tree);
		input.close();
	}

	/**
	 * 由中序和后序构建树
	 * 
	 * @param ldr
	 *            中序
	 * @param lrd
	 *            后序
	 * @return
	 */
	public static TreeNode buildTree(List<String> ldr, List<String> lrd) {
		
		if (ldr == null || lrd == null) {
			return null;
		}
		
		if(ldr.size() == 0 || lrd.size() == 0) {
			return null;
		}

		if (ldr.size() != lrd.size()) {
			return null;
		}

		int len = ldr.size();
		int rootIndex = -1;
		TreeNode node = new TreeNode(lrd.get(len - 1), null, null);
		for (int i = 0; i < len; i++) {
			if (lrd.get(len - 1).equals(ldr.get(i))) {
				rootIndex = i;
				break;
			}
		}

		List<String> ldrLeft = new ArrayList<String>(); // 左子树中序
		List<String> lrdLeft = new ArrayList<String>(); // 左子树后序
		for (int i = 0; i < rootIndex; i++) {
			ldrLeft.add(ldr.get(i));
			lrdLeft.add(lrd.get(i));
		}

		List<String> ldrRight = new ArrayList<String>(); // 右子树中序
		List<String> lrdRight = new ArrayList<String>(); // 右子树后序
		for (int i = rootIndex + 1; i < len; i++) {
			ldrRight.add(ldr.get(i));
			lrdRight.add(lrd.get(i - 1));
		}

		node.left = buildTree(ldrLeft, lrdLeft);
		node.right = buildTree(ldrRight, lrdRight);

		return node;
	}

	public static void levelTree(TreeNode rootNode) {
		Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
		nodeQueue.add(rootNode);
		TreeNode node = null;
		while((node = nodeQueue.poll())!=null) {
			
			if(node.left != null) {
				nodeQueue.add(node.left);
			}
			if(node.right != null) {
				nodeQueue.add(node.right);
			}
			if(node.data == rootNode.data) {
				System.out.print(node.data);
			}else {
				System.out.print(" " + node.data);
			}
		}
	}
}

//class TreeNode {
//	public String data;
//	public TreeNode left;
//	public TreeNode right;
//
//	public TreeNode() {
//	}
//
//	public TreeNode(String data, TreeNode left, TreeNode right) {
//		super();
//		this.data = data;
//		this.left = left;
//		this.right = right;
//	}
//}
