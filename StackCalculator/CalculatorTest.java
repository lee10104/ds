import java.io.*;
import java.util.Stack;

public class CalculatorTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;

				command(input);
			}
			catch (Exception e)
			{
				System.out.println("ERROR");
			}
		}
	}

	public static boolean isNumeric(String str)
	{
		return str.matches("-?\\d+?");
	}
	private static void command(String input) throws Exception
	{
		if (input.matches(".*\\d+\\s+\\d+.*"))
			throw new Exception();
		input = input.replaceAll("\\s+","");
		String result_string = InfixToPostfix.postfix(input);
		result_string = result_string.trim();
			
		long result = Calculate.cal(result_string);
		System.out.println(result_string);
		System.out.println(result);	
	}
}

class InfixToPostfix
{
	static String postfix(String infix) throws Exception
	{
		int error = 0;
		int len = infix.length();
		Stack <Character> sns = new Stack<Character>();
		StringBuilder postfix = new StringBuilder();
		int a = 0;
		int b = -1;
		int parleft = 0;
		int parright = 0;
		for (int i = 0; i < len; i++)
		{
			char ch = infix.charAt(i);
			if (ch == '(')
				parleft += 1;
			if (ch == ')')
				parright += 1;
			//unary를 ~로 바꿔줌
			if (ch == '-')
			{
				if (i == 0)
					ch = '~';
				else
					{
						char t = infix.charAt(i - 1);
						if (t == '+' || t == '-' || t == '*' || t == '/' || t == '%' || t == '(')
							ch = '~';
					}
				
			}
			//postfix로 변환
			if ('0' <= ch  && ch <= '9')
			{
				if (a == 1)
					postfix.append(ch);
				else
				{
					postfix.append(" ");
					postfix.append(ch);
				}
				a = 1;
				b++;
			}
			else if (ch == '+' || ch == '-' || ch == '(' || ch == '*' || ch == '^' || ch == '/' || ch == '%' || ch == '~')
			{
				if (ch == '(')
				{
					if (a == 1)
						throw new Exception();
					b = -1;
				}
				if (a == 0 && ch != '~' && ch != '(')
					error = 1;
				if (sns.isEmpty())
				{
					sns.push(ch);
				}
				else
				{
					if (ch == '(' || ch == '~' || ch == '^')
						sns.push(ch);
					if (ch != '(' && ch != ')' && ch != '^' && ch != '~')
					{
						while (sns.isEmpty() == false && Priority.priority(sns.peek()) >= Priority.priority(ch))
						{
							postfix.append(" ");
							postfix.append(sns.pop());
						}
						sns.push(ch);
					}
				}
				a = 0;
				b++;
			}
			else if (ch == ')')
			{
				if (b == 0)
					error =  1;
				while (sns.peek() != '(')
				{
					postfix.append(" ");
					postfix.append(sns.pop());
					if (sns.isEmpty())
						error = 1;
				}
				sns.pop();
			}
			//다른 연산자 때 ERROR 출력
			else
			{
				error = 1;
				break;
			}
		}
		while (sns.isEmpty() == false)
		{
			char fuck = sns.pop();
			if (fuck == '(')
				continue;
			postfix.append(" ");
			postfix.append(fuck);
		}
		if (parleft != parright)
			error = 1;
		if (error == 1) throw new Exception();
			
		return postfix.toString();
	}
}

class Priority
{
	static int priority(char op)
	{
		int priority = -1;
		if (op == '+')
			priority = 1;
		if (op == '-')
	    	priority = 1;
		if (op == '*')
	    	priority = 2;
		if (op == '/')
	    	priority = 2;
		if (op == '^')
	    	priority = 3;
		if (op == '(')
	    	priority = 0;
		if (op == ')')
			priority = 0;
		if (op == '%')
	    	priority = 2;
		if (op == '~')
	    	priority = 4;
		return priority;
	}
}

class Calculate
{
	static long cal(String input) throws Exception
	{
		long a;
		long b;
		long result = 0;
		Stack <Long> etiquitte = new Stack<Long>();
		String array[] = input.split(" ");
		for (int i = 0; i < array.length; i++)
		{
			if (CalculatorTest.isNumeric(array[i]))
				etiquitte.push(Long.parseLong(array[i]));
			if (etiquitte.empty())
				throw new Exception();
			else if (array[i].equals("^"))
			{
				b = etiquitte.pop();
				a = etiquitte.pop();
				if (a == 0 && b < 0)
					throw new Exception("ERROR");
				result = (long) Math.pow(a, b);
				etiquitte.push(result);
			}
			else if (array[i].equals("*"))
			{
				b = etiquitte.pop();
				a = etiquitte.pop();
				result = a * b;
				etiquitte.push(result);
			}
			else if (array[i].equals("/"))
			{
				b = etiquitte.pop();
				a = etiquitte.pop();
				result = a / b;
				etiquitte.push(result);
			}
			else if (array[i].equals("%"))
			{
				b = etiquitte.pop();
				a = etiquitte.pop();
				result = a % b;
				etiquitte.push(result);
			}
			else if (array[i].equals("+"))
			{
				b = etiquitte.pop();
				a = etiquitte.pop();
				result = a + b;
				etiquitte.push(result);
			}
			else if (array[i].equals("-"))
			{
				b = etiquitte.pop();
				a = etiquitte.pop();
				result = a - b;
				etiquitte.push(result);
			}
			else if (array[i].equals("~"))
			{
				a = etiquitte.pop();
				result = (-1) * a;
				etiquitte.push(result);
			}
		}
		return result;
	}
}