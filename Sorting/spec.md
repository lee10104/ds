# Sorting

1. 목표
여러가지 정렬 알고리즘을 구현하고 성능을 비교하면서 알고리즘의 중요성을 깨닫도록 합니다.

2. 문제
아래와 같은 여러 가지 정렬 알고리즘을 구현하고 비교해 봅니다.

[B] Bubble Sort
[I] Insertion Sort
[H] Heap Sort
[M] Merge Sort
[Q] Quick Sort
[R] Radix Sort

3. 뼈대 코드
import java.io.*;
import java.util.*;

public class SortingTest
{
  public static void main(String args[])
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try
    {
      boolean isRandom = false; // 입력받은 배열이 난수인가 아닌가?
      int[] value;  // 입력 받을 숫자들의 배열
      String nums = br.readLine();  // 첫 줄을 입력 받음
      if (nums.charAt(0) == 'r')
      {
        // 난수일 경우
        isRandom = true;  // 난수임을 표시

        String[] nums_arg = nums.split(" ");

        int numsize = Integer.parseInt(nums_arg[1]);  // 총 갯수
        int rminimum = Integer.parseInt(nums_arg[2]); // 최소값
        int rmaximum = Integer.parseInt(nums_arg[3]); // 최대값

        Random rand = new Random(); // 난수 인스턴스를 생성한다.

        value = new int[numsize]; // 배열을 생성한다.
        for (int i = 0; i < value.length; i++)  // 각각의 배열에 난수를 생성하여 대입
          value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
      }
      else
      {
        // 난수가 아닐 경우
        int numsize = Integer.parseInt(nums);

        value = new int[numsize]; // 배열을 생성한다.
        for (int i = 0; i < value.length; i++)  // 한줄씩 입력받아 배열원소로 대입
          value[i] = Integer.parseInt(br.readLine());
      }

      // 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
      while (true)
      {
        int[] newvalue = (int[])value.clone();  // 원래 값의 보호를 위해 복사본을 생성한다.

        String command = br.readLine();

        long t = System.currentTimeMillis();
        switch (command.charAt(0))
        {
          case 'B': // Bubble Sort
            newvalue = DoBubbleSort(newvalue);
            break;
          case 'I': // Insertion Sort
            newvalue = DoInsertionSort(newvalue);
            break;
          case 'H': // Heap Sort
            newvalue = DoHeapSort(newvalue);
            break;
          case 'M': // Merge Sort
            newvalue = DoMergeSort(newvalue);
            break;
          case 'Q': // Quick Sort
            newvalue = DoQuickSort(newvalue);
            break;
          case 'R': // Radix Sort
            newvalue = DoRadixSort(newvalue);
            break;
          case 'X':
            return; // 프로그램을 종료한다.
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

  ////////////////////////////////////////////////////////////////////////////////////////////////////
  private static int[] DoBubbleSort(int[] value)
  {
    // TODO : Bubble Sort 를 구현하라.
    // value는 정렬안된 숫자들의 배열이며 value.length 는 배열의 크기가 된다.
    // 결과로 정렬된 배열은 리턴해 주어야 하며, 두가지 방법이 있으므로 잘 생각해서 사용할것.
    // 주어진 value 배열에서 안의 값만을 바꾸고 value를 다시 리턴하거나
    // 같은 크기의 새로운 배열을 만들어 그 배열을 리턴할 수도 있다.
    return (value);
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////
  private static int[] DoInsertionSort(int[] value)
  {
    // TODO : Insertion Sort 를 구현하라.
    return (value);
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////
  private static int[] DoHeapSort(int[] value)
  {
    // TODO : Heap Sort 를 구현하라.
    return (value);
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////
  private static int[] DoMergeSort(int[] value)
  {
    // TODO : Merge Sort 를 구현하라.
    return (value);
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////
  private static int[] DoQuickSort(int[] value)
  {
    // TODO : Quick Sort 를 구현하라.
    return (value);
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////
  private static int[] DoRadixSort(int[] value)
  {
    // TODO : Radix Sort 를 구현하라.
    return (value);
  }
}

중요
이번 숙제는 뼈대 코드를 필히 사용해야 합니다. (같은 입출력 여건에서의 객관적인 비교를 위함)
뼈대 코드를 사용하지 않을 경우 입출력 문제나, 잘못된 시간체크 연산에 의해 불이익을 받을 수 있습니다.
또한 뼈대 코드의 내용을 이해해서 자신의 것으로 만들기 바랍니다.
뼈대 코드의 마지막 부분에 각각의 소팅에 대한 함수가 있습니다. 거기서부터 구현을 시작하면 됩니다.

4. 입력 형식숫자 입력 -> 소팅 방법
입력은 숫자의 나열로 이루어지며, 첫 줄에 총 숫자의 갯수, 둘째줄부터 각각의 숫자가 입력됩니다.
  9              (총 숫자의 갯수)
  100
  7
  925
  -234
  10
  -9999
  12738
  239
  -2391          (여기까지 총 9개)
숫자의 나열 대신 난수를 입력할 수도 있습니다. 이 때는 첫 줄에 r (갯수) (최소값) (최대값) 이 오고 끝납니다.
r 1000 -30000 30000        (-30000 에서 30000 까지(-30000과 30000도 포함) 1000개의 숫자를 랜덤하게 생성하고 그것을 입력으로 대신한다)
숫자의 나열이 정의 되었으면 다음 줄에서 소팅 방법이 입력됩니다.
방법은 B, I, H, M, Q, R (2번 항목의 소팅 종류 참조) 중의 한 글자로 입력됩니다.
소팅 방법이 입력되면 소팅을 수행하고 수행결과를 출력합니다.
출력한 다음 다시 소팅 방법을 입력받습니다. (3번으로 돌아감)
만약 소팅 방법에 X가 입력되었다면 종료합니다.
모든 숫자는 정수이며 int의 범위 내에 속합니다. 숫자들의 총 갯수는 1000000개 이하입니다.

5. 출력 형식2가지 입력 방법(숫자의 나열 or 난수)에 따라 다르게 출력
숫자의 나열이 입력되었다면 정렬된 숫자들을 오름차순(작은값에서 큰값으로) 으로 한 값당 한줄에 출력합니다.(정렬 결과 출력)
(작성한 코드가 정렬을 잘 수행하는지를 확인하고자 하는 목적)
  -9999
  -2391
  -234
  7
  9
  10
  100
  239
  925
난수가 입력되었다면 정렬을 수행하되 결과는 출력할 필요 없고 대신 수행 시간(ms)를 출력합니다.
(작성한 코드가 각각의 정렬 방법에 따라 어느 정도의 시간이 걸리는지를 알아보고자 하는 목적)
뼈대 코드에 구현이 되어 있습니다.

6. 입출력 예제
  $ java SortingTest        <- 프로그램 실행
  5                         <- 이렇게 입력(숫자가 총 5개라는 뜻)
  3                         <- 이렇게 입력(첫번째 숫자)
  1                         <- 이렇게 입력(두번째 숫자)
  9                         <- 이렇게 입력(세번째 숫자)
  3                         <- 이렇게 입력(네번째 숫자)
  4                         <- 이렇게 입력(다섯번째 숫자)
  Q                         <- 이렇게 입력(Quick Sort를 수행하라)
  1                         <- 이렇게 출력(QuickSort로 오름차순 정렬한 첫번째 숫자)
  3                         <- 이렇게 출력(QuickSort로 오름차순 정렬한 두번째 숫자)
  3                         <- 이렇게 출력(QuickSort로 오름차순 정렬한 세번째 숫자)
  4                         <- 이렇게 출력(QuickSort로 오름차순 정렬한 네번째 숫자)
  9                         <- 이렇게 출력(QuickSort로 오름차순 정렬한 다섯번째 숫자)
  B                         <- 이렇게 입력(Bubble Sort를 수행하라)
  1                         <- 이렇게 출력(BubbleSort로 오름차순 정렬한 첫번째 숫자)
  3                         <- 이렇게 출력(BubbleSort로 오름차순 정렬한 두번째 숫자)
  3                         <- 이렇게 출력(BubbleSort로 오름차순 정렬한 세번째 숫자)
  4                         <- 이렇게 출력(BubbleSort로 오름차순 정렬한 네번째 숫자)
  9                         <- 이렇게 출력(BubbleSort로 오름차순 정렬한 다섯번째 숫자)
  X                         <- 이렇게 입력(이제 종료해라)
  $                         <- 프로그램 종료

  $ java SortingTest        <- 프로그램 실행
  r 100 -200 200            <- 이렇게 입력(숫자가 총 100개이며 범위는 -200 에서 200 사이에서 랜덤하게 생성)
  Q                         <- 이렇게 입력(Quick Sort를 수행하라)
  100 ms                    <- 이렇게 출력(QuickSort를 수행하는데 걸린 시간.)
  B                         <- 이렇게 입력(Bubble Sort를 수행하라)
  10000 ms                  <- 이렇게 출력(BubbleSort를 수행하는데 걸린 시간.)
  X                         <- 이렇게 입력(이제 종료해라)
  $                         <- 프로그램 종료

7. 보고서
이번 과제는 여러 정렬 알고리즘을 비교하는 것이 목적이므로 보고서를 받습니다. 보고서는 다음의 내용을 포함해야 합니다.

정렬 알고리즘의 동작 방식. 정렬의 경우 실행 결과만으로 알고리즘에 맞게 구현했는지 알 수 없으므로, 이에 대한 설명을 적습니다.
동작 시간 분석. 정렬에 걸린 시간을 측정하고 이를 분석합니다. 특히, data의 개수에 따른 분석은 반드시 포함하도록 합니다.
단, 다음의 경우 감점이 될 수 있습니다.

분량이 지나치게 많은 경우. 전달하고자 하는 내용을 간결하게 요약하는 것이 중요합니다.
알고리즘 설명 시 다른 곳의 설명을 단순히 복사한 경우. 자신이 알고리즘의 원리를 이해했다는 것을 보여야 합니다.
구현 내용을 일일이 설명하려 하는 경우. 핵심 내용에 대한 설명만 적도록 합니다.
실험 결과를 단순히 나열한 경우. 실험 결과 역시 요약해서 적습니다. 가령 같은 조건에서 여러 번 실험한 경우, 실험 횟수/최대값/최소값/평균/표준편차를 적는 정도로 충분합니다.

8. 참고사항
효율적인 알고리즘 사용 여부가 점수에 영향을 줄 수 있습니다.
수행 시간은 입출력 부분은 빼고 실제 소팅 시간만 재도록 합니다.
교재, 강의노트의 code나 Java 표준 API를 이용해도 좋습니다.(단 5번 주의사항 유의) 하지만 그밖의 것은 직접 만들도록 합니다.
과목 게시판에 과제에 대한 질문 및 주의사항이 공지됩니다. 이를 수시로 확인하시기 바랍니다.
부정행위에 관한 주의사항을 읽기 바랍니다.
