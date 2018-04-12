package algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * YTUOJ 2342: 二叉树 根据前序和中序构建二叉树，进行层次遍历
 * 
 * @author ccq
 *
 */
public class TreeTest5 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		List<String> ldrList = null;
		List<String> lrdList = null;
		String str;
		while(input.hasNext()) {
			str = input.nextLine();
			ldrList = new ArrayList<String>();
			lrdList = new ArrayList<String>();
			String str1 = str.split(" ")[0];
			for (int i = 0; i < str1.length(); i++) {
				lrdList.add(String.valueOf(str1.charAt(i)));
			}
			String str2 = str.split(" ")[1];
			for (int i = 0; i < str2.length(); i++) {
				ldrList.add(String.valueOf(str2.charAt(i)));
			}
			TreeNode tree = buildTree(ldrList, lrdList);
			//LRDTree(tree);
			LRDTree2(tree);
			System.out.println();
		}
		input.close();
	}

	/**
	 * 由中序和后序构建树
	 * 
	 * @param ldr
	 *            中序
	 * @param dlr
	 *            前序
	 * @return
	 */
	public static TreeNode buildTree(List<String> ldr, List<String> dlr) {
		
		if (ldr == null || dlr == null) {
			return null;
		}
		
		if(ldr.size() == 0 || dlr.size() == 0) {
			return null;
		}

		if (ldr.size() != dlr.size()) {
			return null;
		}

		int len = ldr.size();
		int rootIndex = -1;
		TreeNode node = new TreeNode(dlr.get(0), null, null);
		for (int i = 0; i < len; i++) {
			if (dlr.get(0).equals(ldr.get(i))) {
				rootIndex = i;
				break;
			}
		}

		List<String> ldrLeft = new ArrayList<String>(); // 左子树中序
		List<String> dlrLeft = new ArrayList<String>(); // 左子树前序
		for (int i = 0; i < rootIndex; i++) {
			ldrLeft.add(ldr.get(i));
			dlrLeft.add(dlr.get(i + 1));
		}

		List<String> ldrRight = new ArrayList<String>(); // 右子树中序
		List<String> dlrRight = new ArrayList<String>(); // 右子树前序
		for (int i = rootIndex + 1; i < len; i++) {
			ldrRight.add(ldr.get(i));
			dlrRight.add(dlr.get(i));
		}

		node.left = buildTree(ldrLeft, dlrLeft);
		node.right = buildTree(ldrRight, dlrRight);

		return node;
	}

	public static void LRDTree(TreeNode rootNode) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode pre = null;
		TreeNode cur = null;
		stack.push(rootNode);
		while(!stack.isEmpty()) {
			
			cur = stack.peek();
			if((cur.left == null && cur.right == null) || pre!= null &&(cur.left == pre || cur.right == pre)) {
				System.out.print(cur.data);
				stack.pop();
				pre = cur;
			}else {
				if (cur.right != null) {
					stack.push(cur.right);
				}
				if (cur.left != null) {
					stack.push(cur.left);
				}
			}
		}
		System.out.println();
	}
	public static void LRDTree2(TreeNode rootNode) {
		if(rootNode != null) {
			LRDTree2(rootNode.left);
			LRDTree2(rootNode.right);
			System.out.print(rootNode.data);
		}
	}
}

class TreeNode {
	public String data;
	public TreeNode left;
	public TreeNode right;

	public TreeNode() {
	}

	public TreeNode(String data, TreeNode left, TreeNode right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
