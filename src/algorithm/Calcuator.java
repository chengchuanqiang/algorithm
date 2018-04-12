package algorithm;

import java.util.Stack;

/**
 * 使用前缀表达式实现四则混合运算
 * @author Administrator
 *
 */
public class Calcuator {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 将val变为前缀表达式
	 */
	public static void prefix(String val) {
		Stack<Character> operatorStack = new Stack<>();
		Stack<String> valStack = new Stack<>();
		Character temp;
		for(int i=0; i < val.length(); i++) {
			switch(val.charAt(i)) {
			case '+':
			case '-':
				while(operatorStack.size() > 0) {
					temp = operatorStack.peek();
					if(temp == ')' || temp == '+' || temp == '-') {
						break;
					}else {
						valStack.push(String.valueOf(operatorStack.pop()));
					}
				}
				operatorStack.push(val.charAt(i));
				break;
			case '*':
			case '/':
				
				break;
			}
		}
		
	}
	
	
}
