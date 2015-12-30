import java.io.*;
import java.util.*;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// �Է¹��� �迭�� �����ΰ� �ƴѰ�?
			int[] value;	// �Է� ���� ���ڵ��� �迭
			String nums = br.readLine();	// ù ���� �Է� ����
			if (nums.charAt(0) == 'r')
			{
				// ������ ���
				isRandom = true;	// �������� ǥ��

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// �� ����
				int rminimum = Integer.parseInt(nums_arg[2]);	// �ּҰ�
				int rmaximum = Integer.parseInt(nums_arg[3]);	// �ִ밪

				Random rand = new Random();	// ���� �ν��Ͻ��� �����Ѵ�.

				value = new int[numsize];	// �迭�� �����Ѵ�.
				for (int i = 0; i < value.length; i++)	// ������ �迭�� ������ �����Ͽ� ����
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// ������ �ƴ� ���
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// �迭�� �����Ѵ�.
				for (int i = 0; i < value.length; i++)	// ���پ� �Է¹޾� �迭���ҷ� ����
					value[i] = Integer.parseInt(br.readLine());
			}

			// ���� �Է��� �� �޾����Ƿ� ���� ����� �޾� �׿� �´� ������ �����Ѵ�.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// ���� ���� ��ȣ�� ���� ���纻�� �����Ѵ�.
				
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
						return;	// ���α׷��� �����Ѵ�.
					default:
						throw new IOException("�߸��� ���� ����� �Է��߽��ϴ�.");
				}
				if (isRandom)
				{
					// ������ ��� ����ð��� ����Ѵ�.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				}
				else
				{
					// ������ �ƴ� ��� ���ĵ� ������� ����Ѵ�.
					for (int i = 0; i < newvalue.length; i++)
					{
						System.out.println(newvalue[i]);
					}
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("�Է��� �߸��Ǿ����ϴ�. ���� : " + e.toString());
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

	//���� ���� �˰��� ������ ������ ����
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

	//http://proneer.tistory.com/292�� �˰��� ����
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

	//wikipedia�� �˰��� ����
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
	
	//http://pelican7.egloos.com/1206802�� �˰��� ����
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