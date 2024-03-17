package main;
import java.util.*;

public class LinkedStack
{
	
	static int order(char a)
	{
		switch (a)  //creating switch for priority deciding
		{
		case '+':
		case '-':
			return 1;
	
		case '*':
		case '/':
			return 2;
	
		case '^':
			return 3;
		}
		return -1;
	}
	

	static String conversion(String a) //creating cinversion function
	{

		String res = new String("");//blank string is created
		
		Stack<Character> ss = new Stack<>();//stack is created
		
		for (int i = 0; i<a.length(); ++i) //iterarting
		{
			char c = a.charAt(i);//choosing character of string one by one
			
			if (Character.isLetterOrDigit(c))//if it is operand it will simply add to string
				res += c;
		
			else if (c == '(')
				ss.push(c); // if it is left paranthesis it will simply push into stack
			
			else if (c == ')') //if right paranthesis then character will add string upto left paranthesis not meet or stack becomes empty
			{
				while (!ss.isEmpty() &&
						ss.peek() != '(')
					res += ss.pop();
				
					ss.pop();
			}
			else 
			{
				while (!ss.isEmpty() && order(c)   //if it is token then on basis of priority  it goes on stack only when lowest priotiy tokens will there otherwise pop them
						<= order(ss.peek())){
					
					res += ss.pop();
			}
				ss.push(c);
			}
	
		}
	
		while (!ss.isEmpty()){
			if(ss.peek() == '('){   // if we found that left paranthesis it means there is no right paranthesis would exist hence it is invalid
				return "invalid data given";
			}
			res += ss.pop(); //otherwise finally popping element and adding it to res
		}
		return res;//finally returning res
	}

	public static void main(String[] args)
	{
		String z ="a*b-(c+d)+e";
		System.out.println(conversion(z));
	}
}
