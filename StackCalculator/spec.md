# Stackcalculator

1. 개요
스택을 이용하여 간단한 계산기를 만들어 봅니다.

2. 뼈대 코드
import java.io.*;

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
        System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
      }
    }
  }

  private static void command(String input)
  {
    // TODO : 아래 문장을 삭제하고 구현해라.
    System.out.println("<< command 함수에서 " + input + " 명령을 처리할 예정입니다 >>");
  }
}

이 코드를 기본으로 하여 내용을 추가하도록 합니다.
이 코드에는 제출을 위한 입출력과 파일이름만이 정의되어 있습니다.

3. 계산기 명세
자바의 long 범위에서 정수 연산을 지원하도록 합니다.
지원하는 연산자 : +, -, *, /, %, ^(지수 연산), 소괄호 : '(' ')'
괄호는 소괄호 ()만 사용합니다.
우선순위는 일반 수식 연산과 같습니다. 구체적으로 아래와 같으며 위쪽이 아래쪽보다 우선 순위가 높습니다. ^ 거듭제곱은 right-associative, 나머지는 left-associative 연산자입니다.
( )
^
- (unary)
* / %
+ - (binary)
각 기호와 기호 또는 숫자와 기호 사이에는 공백(tab, space)이 여러 개 들어갈 수 있습니다. 그러나 숫자들은 붙여 쓰도록 합니다.

4. 실행 예
프로그램을 실행하면 infix expression을 입력받습니다. 이것을 postfix expression으로 변환한 다음 postfix expression과 계산 결과를 출력하도록 합니다. 수식이 postfix expression으로 변환되면, unary '-' 와 binary '-'는 구분이 되지 않습니다. 따라서 postfix expression으로 변환할 때 unary '-' 는 '~'로 변환해 주십시오. postfix expression출력시에 unary '-'의 경우는 '~'로 출력되야 합니다. 수식 입력 대신 q를 입력하면 프로그램을 종료합니다.

  % java CalculatorTest   <- 프로그램 실행
  (3 + 4)^2 - 3 * (2 + 3)   <- 이렇게 입력하면
  3 4 + 2 ^ 3 2 3 + * -   <- 이렇게 출력
  34        <- 이렇게 출력한다.
  3 * 4 - 5 * 6      <- 이렇게 입력하면
  3 4 * 5 6 * -     <- 이렇게 출력
  -18       <- 이렇게 출력한다.
  100 / 2 ^ 2      <- 이렇게 입력하면
  100 2 2 ^ /     <- 이렇게 출력
  25        <- 이렇게 출력한다.
  2^2^3        <- 이렇게 입력하면
  2 2 3 ^ ^       <- 이렇게 출력
  256       <- 이렇게 출력한다.
  2^^3        <- 이렇게 입력하면
  ERROR       <- 이렇게 출력한다.
  - 51313      <- 이렇게 입력하면
  51313 ~       <- 이렇게 출력
  -51313       <- 이렇게 출력한다.
  q       <- 이렇게 입력하면
  %         <- 종료한다.
입력은 공백이 아무리 많이 있더라도 정상적으로 인식하여야 합니다. (물론 공백 없이 사용해도 인식이 되어야 합니다)

postfix expression을 출력시에는, 각 숫자들이 구분이 되도록 띄어 쓰도록 합니다. 기호와 기호 사이, 기호와 숫자 사이도 띄어 써야 합니다.

5. 참고사항
주어진 CalculatorTest 클래스 내에서 구현하여 $ java CalculatorTest 같은 식으로 실행할 수 있도록 합니다. 클래스 내에서 함수를 더 추가하거나 private 클래스를 더 만드는 건 상관없습니다.
에러 처리를 잘 해야 합니다. 여기서 에러는 짝이 맞지 않는 괄호, 잘못된 연산자, 정의되지 않은 기호 등을 포함합니다.
에러 메시지는 'ERROR' 라고 출력하도록 합니다.
수식을 파싱하는 과정, 혹은 postfix로 변환하는 과정에서 에러가 발생할 경우가 이에 해당합니다.
과목 게시판에 과제에 대한 질문 및 주의사항이 공지됩니다. 이를 수시로 확인하시기 바랍니다.
부정행위에 관한 주의사항을 읽기 바랍니다.
