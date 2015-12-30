import java.io.*;
import java.util.*;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// 입력받은 배열이 난수인가 아닌가?
			int[] value;	// 입력 받을 숫자들의 배열
			String nums = br.readLine();	// 첫 줄을 입력 받음
			if (nums.charAt(0) == 'r')
			{
				// 난수일 경우
				isRandom = true;	// 난수임을 표시

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// 총 갯수
				int rminimum = Integer.parseInt(nums_arg[2]);	// 최소값
				int rmaximum = Integer.parseInt(nums_arg[3]);	// 최대값

				Random rand = new Random();	// 난수 인스턴스를 생성한다.

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 각각의 배열에 난수를 생성하여 대입
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// 난수가 아닐 경우
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 한줄씩 입력받아 배열원소로 대입
					value[i] = Integer.parseInt(br.readLine());
			}

			// 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// 원래 값의 보호를 위해 복사본을 생성한다.
				
				String command = br.readLine();
				long t = System.currentTimeMillis();
				switch (command.charAt(0))
				{
					case 'B':	// Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':	// Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':	// Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':	// Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':	// Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':	// Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;	// 프로그램을 종료한다.
					default:
						throw new IOException("잘못된 정렬 방법을 입력했습니다.");
				}
				if (isRandom)
				{
					// 난수일 경우 수행시간을 출력한다.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				}
				else
				{
					// 난수가 아닐 경우 정렬된 결과값을 출력한다.
					for (int i = 0; i < newvalue.length; i++)
					{
						System.out.println(newvalue[i]);
					}
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
		}
	}

	private static int[] DoBubbleSort(int[] value)
	{
		for (int i = 0; i < value.length - 1; i++)
		{
			for (int j = 0; j < value.length - i - 1; j++)
			{
				if (value[j] > value[j+1])
				{
					int tmp = value[j];
					value[j] = value[j+1];
					value[j+1] = tmp;
				}
			}
		}
		return (value);
	}

	private static int[] DoInsertionSort(int[] value)
	{
		for (int i = 1; i < value.length; i++)
		{
			int tmp = value[i];
			int a = 0;
			for (int j = i - 1; j >= 0; j--)
			{
				if (value[j] <= tmp)
					break;
				value[j + 1] = value[j];
				a++;
			}
			value[i - a] = tmp;
		}
		return (value);
	}

	//쉽게 배우는 알고리즘 지은이 문병로 참고
	private static int[] DoHeapSort(int[] value)
	{
		heapSort(value, value.length);
		return (value);
	}
	
	private static void buildHeap(int[] value, int n)
	{
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(value, i, n);
	}
	
	private static void heapify(int[] value, int k, int n)
	{
		int left = 2 * (k + 1) - 1;
		int right = 2 * (k + 1);
		int larger;
		if (right < n)
		{
			if (value[left] > value[right])
				larger = left;
			else
				larger = right;
		}
		else if (left < n)
			larger = left;
		else
			return;
		if (value[larger] > value[k])
		{
			int tmp = value[k];
			value[k] = value[larger];
			value[larger] = tmp;
			heapify(value, larger, n);
		}
	}
	
	private static void heapSort(int[] value, int n)
	{
		buildHeap(value, n);
		for (int i = n - 1; i >= 0; i--)
		{
			int tmp = value[0];
			value[0] = value[i];
			value[i] = tmp;
			heapify(value, 0, i);
		}
	}

	//http://proneer.tistory.com/292의 알고리즘 참고
	private static int[] DoMergeSort(int[] value)
	{
		mergeSort(value);
		return (value);
	}
	
	private static void mergeSort(int[] value)
	{
		if (value.length > 1)
		{
			int i = value.length / 2;
			int j = value.length - i;
			int[] S1;
			int[] S2;
			S1 = new int[i];
			S2 = new int[j];
			for (int a = 0; a < i; a++)
				S1[a] = value[a];
			for (int a = 0; a < j; a++)
				S2[a] = value[a + i];
			mergeSort(S1);
			mergeSort(S2);
			merge(S1, S2, value);
		}
	}
	
	private static void merge(int[]S1, int[]S2, int[]value)
	{
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < S1.length && j < S2.length)
		{
			if (S1[i] > S2[j])
			{
				value[k] = S2[j];
				j++;
			}
			else
			{
				value[k] = S1[i];
				i++;
			}
			k++;
		}
		if (i == S1.length)
		{
			for (int a = 0; a < S2.length - j; a++)
			{
				value[k] = S2[j + a];
				k++;
			}
		}
		else if (j == S2.length)
		{
			for (int a = 0; a < S1.length - i; a++)
			{
				value[k] = S1[i + a];
				k++;
			}
		}
	}

	//wikipedia의 알고리즘 참고
	private static int[] DoQuickSort(int[] value)
	{
		quickSort(value, 0, value.length - 1);
		return (value);
	}
	
	private static void quickSort(int[] value, int left, int right)
	{
		if (value.length > 1)
		{
			if (left < right)
			{
				int newpivot = partition(value, left, right);
				quickSort(value, left, newpivot - 1);
				quickSort(value, newpivot + 1, right);
			}
		}
	}
	
	private static int partition(int[] value, int left, int right)
	{
		int pivot = value[right];
		int a = left;
		for (int i = left; i < right; i++)
		{
			if (value[i] < pivot)
			{
				int tmp = value[i];
				value[i] = value[a];
				value[a] = tmp;
				a++;
			}
		}
		int tmp = value[a];
		value[a] = value[right];
		value[right] = tmp;
		return a;
	}
	
	//http://pelican7.egloos.com/1206802의 알고리즘 참고
	private static int[] DoRadixSort(int[] value)
	{
		int[] temp;
		temp = new int[value.length];
		for (int po = 0; po < 10; po++)
		{
			int pval = (int) Math.pow(10, po);
			int[] count;
			count = new int[19];
			for (int i = 0; i < value.length; i++)
			{
				int index = value[i] / pval % 10;
				count[index + 9]++;
			}
			for (int i = 1; i < 19; i++)
				count[i] = count[i] + count[i-1];
			for (int i = value.length - 1; i >= 0; i--)
			{
				int index = value[i] / pval % 10;
				temp[count[index + 9] - 1] = value[i];
				count[index + 9]--;
			}
			for (int i = 0; i < value.length; i++)
				value[i] = temp[i];
		}
		return (value);
	}
}