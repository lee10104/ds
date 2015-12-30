import java.io.*; //�Է��� �ޱ� ���� �� ���̺귯���� �ʿ��ϴ�.

public class BigInteger
{
	public static void main(String[] args)
	{
		// �Է��� �ޱ� ���� �۾��̴�.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// quit�� ���� �� ���� �Է��� �޾ƾ� �ϹǷ�
		// while(true) ���� ����Ͽ� ��� �ݺ��Ѵ�.
		while (true)
		{
			try
			// try �� catch ���� ���� �߻��� �����Ѵ�.
			{
				String input = br.readLine(); // �� ���� �Է� �޾� input ���ڿ��� ����

				if (input.compareTo("quit") == 0)
				{
					// quit ��� �Է¹޾��� ��� �����Ѵ�.
					// �����Ϸ��� while ���� �������;� �ϹǷ� break�� ����Ѵ�.
					break;
				}

				// quit�� �ƴ϶�� ���� ����ؾ� �Ѵ�.
				calculate(input);
			} catch (Exception e)
			{
				// ���� try { } �ȿ��� ������ �߻��ߴٸ� �̰����� ���� �ȴ�.
				// �̷��� �����ν� �Է��� �̻��ϰ� ���� ��� �߻��ϴ� ������ �����Ѵ�.
				System.out.println("�Է��� �߸��Ǿ����ϴ�. ���� : " + e.toString());
			}
		}
	}

	// �� �Լ����� �Է¹��� input ���ڿ��� �̿��Ͽ� ����� �ǽ��Ѵ�.
	// ���� main �Լ��� �Ϻ��ϰ� ������ �� �� �Լ��� ������ �����Ӱ� �����غ���.
	private static void calculate(String input)
	{
		char[] input_array = new char[203];
		char[] temp_biginteger1 = new char[101];
		char[] temp_biginteger2 = new char[101];
		int[] temp_biginteger3 = new int[201];
		char[] biginteger1 = new char[101];
		char[] biginteger2 = new char[101];
		int[] biginteger3 = new int[202];

		//input���� ���� ���ֱ�, ���� ���� ����
		input = input.replaceAll("\\s+", "");
		int len = input.length();

		//�������� index ã��
		int op = input.indexOf('*', 1);
		if (op == -1)
		{
			int op1 = input.indexOf('+', 1);
			int op2 = input.indexOf('-', 1);
			if (op1 == -1)
				op = op2;
			else if (op2 == -1)
				op = op1;
			else if (op1 <= op2)
				op = op1;
			else
				op = op2;
		}

		// input�� �迭�� �־� �����ڸ� �������� temp_biginteger1, temp_biginteger2�� ����
		input_array = input.toCharArray();
		for (int i = 0; i < op; i++)
		{
			temp_biginteger1[i] = input_array[i];
		}
		for (int i = op + 1; i < len; i++)
		{
			temp_biginteger2[i - op - 1] = input_array[i];
		}

		char sign1;
		char sign2;
		int len1;
		int len2;
		// temp_biginteger1�� �� �迭�� ��ȣ�� ���� ������ biginteger1�� �ְ� sign1�� ��ȣ ����
		if (temp_biginteger1[0] == '+' || temp_biginteger1[0] == '-')
		{
			sign1 = temp_biginteger1[0];
			if (op == 2)
			{
				biginteger1[0] = temp_biginteger1[1];
				len1 = 1;
			}
			else
			{
				for (int i = 0; i < op - 1; i++)
				{
					biginteger1[i] = temp_biginteger1[op - 1 - i];
				}
			len1 = op - 1;
			}
		} else
		{
			sign1 = '+';
			for (int i = 0; i < op; i++)
			{
				biginteger1[i] = temp_biginteger1[op - 1 - i];
			}
			len1 = op;
		}
		for (int i = len1; i < 101; i++)
		{
			biginteger1[i] = '0';
		}
		
		// temp_biginteger2�� �� �迭�� ��ȣ�� ���� ������ biginteger2�� �ְ� sign2�� ��ȣ ����
		if (temp_biginteger2[0] == '+' || temp_biginteger2[0] == '-')
		{
			sign2 = temp_biginteger2[0];
			if (len - op == 3)
			{
				biginteger2[0] = temp_biginteger2[1];
				len2 = 1;
			}
			else
			{
				for (int i = 0; i < len - op - 2; i++)
				{
					biginteger2[i] = temp_biginteger2[len - op - 2 - i];
				}
				len2 = len - op - 2;
			}
		} else
		{
			sign2 = '+';
			for (int i = 0; i < len - op - 1; i++)
			{
				biginteger2[i] = temp_biginteger2[len - op - 2 - i];
			}
			len2 = len - op - 1;
		}
		for (int i = len2; i < 101; i++)
		{
			biginteger2[i] = '0';
		}

		//���� ������ �� ũ�� compare = 1, ���� ������ �� ũ�� compare = -1, ������ compare = 0
		int compare = 0;
		if (len1 == len2)
		{
			for (int i = len1 - 1; i >= 0; i--)
			{
				if (biginteger1[i] > biginteger2[i])
				{
					compare = 1;
					break;
				}
				else if (biginteger1[i] < biginteger2[i])
				{
					compare = -1;
					break;
				}
			}
		}
		else if (len1 > len2)
			compare = 1;
		else
			compare = -1;
		//�� ���� ��ȣ�� �����ڸ� ����� ���� ���ϱ�, ����, ���ϱ� ����, ���
		if (input.charAt(op) == '+')
		{
			if (sign1 == '+' && sign2 =='+')
			{
				for (int i = 0; i < 101; i++)
				{
					temp_biginteger3[i] = add(biginteger1, biginteger2)[i];
				}
			}
			else if (sign1 == '+' && sign2 == '-')
			{
				for (int i = 0; i < 101; i++)
				{
					if (compare == 1)
						temp_biginteger3[i] = subtract(biginteger1, biginteger2)[i];
					else
					{
						biginteger3[0] = '-';
						temp_biginteger3[i] = subtract(biginteger2, biginteger1)[i];
					}
				}
			}
			else if (sign1 == '-' && sign2 == '+')
			{
				for (int i = 0; i < 101; i++)
				{
					if (compare == -1)
						temp_biginteger3[i] = subtract(biginteger2, biginteger1)[i];
					else
					{
						biginteger3[0] = '-';
						temp_biginteger3[i] = subtract(biginteger1, biginteger2)[i];
					}
				}
			}
			else if (sign1 == '-' && sign2 == '-')
			{
				for (int i = 0; i < 101; i++)
				{
					temp_biginteger3[i] = add(biginteger1, biginteger2)[i];
				}
				biginteger3[0] = '-';
			}
			for (int i = 0; i < 201; i++)
			{
				biginteger3[i + 1] = temp_biginteger3[200 - i];
			}
		}

		else if (input.charAt(op) == '-')
		{
			if (sign1 == '+' && sign2 =='+')
			{
				for (int i = 0; i < 101; i++)
				{
					if (compare == 1)
						temp_biginteger3[i] = subtract(biginteger1, biginteger2)[i];
					else
					{
						biginteger3[0] = '-';
						temp_biginteger3[i] = subtract(biginteger2, biginteger1)[i];
					}
				}
			}
			else if (sign1 == '+' && sign2 == '-')
			{
				for (int i = 0; i < 101; i++)
				{
					temp_biginteger3[i] = add(biginteger1, biginteger2)[i];
				}
			}
			else if (sign1 == '-' && sign2 == '+')
			{
				for (int i = 0; i < 101; i++)
				{
					temp_biginteger3[i] = add(biginteger1, biginteger2)[i];
				}
				biginteger3[0] = '-';
			}
			else if (sign1 == '-' && sign2 == '-')
			{
				for (int i = 0; i < 101; i++)
				{
					if (compare == 1)
					{
						biginteger3[0] = '-';
						temp_biginteger3[i] = subtract(biginteger1, biginteger2)[i] ;
					}
					else
						temp_biginteger3[i] = subtract(biginteger2, biginteger1)[i];
				}
			}
			for (int i = 0; i < 201; i++)
			{
				biginteger3[i + 1] = temp_biginteger3[200 - i];
			}
		}

		//�� ��ġ�� ���� ���� �� �޾ƿ÷� ����
		else if (input.charAt(op) == '*')
		{
			int c;
			for (int a = 0; a < 100; a++)
			{
				for (int b = 0; b < 100; b++)
				{
					c = a + b;
					temp_biginteger3[c] += (biginteger1[a] - '0') * (biginteger2[b] - '0');
				}
			}
			for (int i = 0; i < 200; i++)
			{
				int n = 0;
				while (temp_biginteger3[i] >= 10)
				{
					temp_biginteger3[i] = temp_biginteger3[i] - 10;
					n += 1;
				}
				temp_biginteger3[i + 1] += n;
				
			}
			for (int i = 0; i < 201; i++)
			{
				biginteger3[i + 1] = temp_biginteger3[200 - i];
			}
			if (sign1 != sign2)
			{
				biginteger3[0] = '-';
			}
		}
		
		//0�� ��ȣ ���ֱ�
		int zero = 0;
		int all = 0;
		for (int i = 1; i < 202; i++)
		{
			all += 1;
			if (biginteger3[i] == 0)
				zero += 1;
		}
		if (all == zero)
			biginteger3[0] = '+';
		
		//���
		if (biginteger3[0] == '-')
		{
			System.out.print('-');
		}
		int n = 0;
		int m = 0;
		for (int i = 1; i < 202; i++)
		{
			m += 1;
			if (biginteger3[i] == 0)
				n += 1;
			if (n != m)
			{
				System.out.print(biginteger3[i]);
			}
			else if (n == 201)
				System.out.print(0);
		}
		System.out.print('\n');
	}
	
	//char array �ΰ��� �޾� �� ��ġ�� ���� ���� �� �޾ƿ÷� int array3�� ����
	private static int[] add(char[] array1, char[] array2)
	{
		int[] array3 = new int[101];
		for (int i = 0; i < 100; i++)
		{
			array3[i] += (array1[i] - '0') + (array2[i] - '0');
			if (array3[i] >= 10)
			{
				array3[i] -= 10;
				array3[i + 1] += 1;
			}
		}
		return array3;
	}
	
	//char array �ΰ��� �޾� �� ��ġ�� ���� �� �� �޾Ƴ��� int array3�� ����
	private static int[] subtract(char[] array1, char[] array2)
	{
		int[] array3 = new int[101];
		for (int i = 0; i < 100; i++)
		{
			array3[i] += (array1[i] -'0') - (array2[i] - '0');
			if (array3[i] < 0)
			{
				array3[i] += 10;
				array3[i + 1] -= 1;
			}
		}
		return array3;
	}

}