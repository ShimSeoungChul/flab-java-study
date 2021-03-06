package 추석숙제.히스토그램;
/*
# 문제 4: 히스토그램
`레이블1:도수1 레이블2:도수2...` 형식의 문자열을 받아서 히스토그램을 출력해주는 프로그램을 작성하라.
히스토그램에서 세로축은 각 레이블의 도수이며, 세로축은 전체 10칸으로 이뤄져야 한다.
한 칸은 가장 큰 도수를 10으로 나눈 값에 해당하며, **반올림**으로 표시한다.
레이블은 모두 한글자(숫자 또는 알파벳 문자)이며, 도수는 `0`부터 자바 `Integer.MAX_VALUE` 사이의 값이다.

입력 예:
```
A:10 B:10
```
출력 예:
```
 10 | # #
    | # #
    | # #
    | # #
    | # #
  5 | # #
    | # #
    | # #
    | # #
    | # #
  0 +----
      A B
```
```
A:7 B:5
```
출력 예:
```
 7 | #
   | #
   | #
   | # #
   | # #
 4 | # #
   | # #
   | # #
   | # #
   | # #
 0 +----
     A B
```
해설:
```
 7 | #           (6.65~7.0)
   | #           (5.95~6.3)
   | #           (5.25~5.6)
   | # #         (4.55~4.9)
   | # #         (3.85~4.2)
 4 | # #         (3.15~3.5)         <----4는 여기까지 #를 표시할 수 있는 최댓값인 3.5를 반올림한 정수
   | # #         (2.45~2.8)
   | # #         (1.75~2.1)
   | # #         (1.05~1.4)
   | # #         (0.35~0.7)
 0 +----
     A B
```
괄호 사이의 값은 전체가 7인 경우 해당 줄까지 #이 표시되기 위한 범위를 보여준다.
 */
public class Histogram {
}
