import java.io.*; //입력을 받기 위해 이 라이브러리가 필요하다.

public class BigInteger
{
	public static void main(String[] args)
	{
		// 입력을 받기 위한 작업이다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// quit가 나올 때 까지 입력을 받아야 하므로
		// while(true) 문을 사용하여 계속 반복한다.
		while (true)
		{
			try
			// try 와 catch 문은 오류 발생을 감지한다.
			{
				String input = br.readLine(); // 한 줄을 입력 받아 input 문자열에 저장

				if (input.compareTo("quit") == 0)
				{
					// quit 라고 입력받았을 경우 종료한다.
					// 종료하려면 while 문을 빠져나와야 하므로 break를 사용한다.
					break;
				}

				// quit가 아니라면 식을 계산해야 한다.
				calculate(input);
			} catch (Exception e)
			{
				// 만약 try { } 안에서 오류가 발생했다면 이곳으로 오게 된다.
				// 이렇게 함으로써 입력을 이상하게 했을 경우 발생하는 오류를 방지한다.
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	// 이 함수에서 입력받은 input 문자열을 이용하여 계산을 실시한다.
	// 위의 main 함수를 완벽하게 이해한 뒤 이 함수의 내용을 자유롭게 구성해보라.
	private static void calculate(String input)
	{
		char[] input_array = new char[203];
		char[] temp_biginteger1 = new char[101];
		char[] temp_biginteger2 = new char[101];
		int[] temp_biginteger3 = new int[201];
		char[] biginteger1 = new char[101];
		char[] biginteger2 = new char[101];
		int[] biginteger3 = new int[202];

		//input에서 공백 없애기, 길이 변수 설정
		input = input.replaceAll("\\s+", "");
		int len = input.length();

		//연산자의 index 찾기
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

		// input을 배열에 넣어 연산자를 기준으로 temp_biginteger1, temp_biginteger2로 나눔
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
		// temp_biginteger1에 들어간 배열을 부호를 빼고 뒤집어 biginteger1에 넣고 sign1에 부호 저장
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
		
		// temp_biginteger2에 들어간 배열을 부호를 빼고 뒤집어 biginteger2에 넣고 sign2에 부호 저장
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

		//앞쪽 정수가 더 크면 compare = 1, 뒤쪽 정수가 더 크면 compare = -1, 같으면 compare = 0
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
		//두 개의 부호와 연산자를 고려해 각각 더하기, 빼기, 곱하기 배정, 계산
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

		//각 위치의 수를 곱한 후 받아올려 연산
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
		
		//0의 부호 없애기
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
		
		//출력
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
	
	//char array 두개를 받아 각 위치의 수를 더한 후 받아올려 int array3을 리턴
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
	
	//char array 두개를 받아 각 위치의 수를 뺀 후 받아내려 int array3을 리턴
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