package 추석숙제.텍스트데이터생성과통계;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/*
# 문제 2: 텍스트 데이터 생성
현재 디렉터리에 `test.txt`라는 파일을 만들고 다음과 같이 내용을 채워넣는 함수를 작성하라.
1. 자바 명령 실행시 명령줄에서 두 양수를 입력으로 받는다(예: `$ java 클래스이름 10 10000`). 첫번째 숫자보다 두번째 숫자가 커야 한다. 잘못된 입력이 발생하면 프로그램을 종료한다.
2. 입력받은 두 숫자 중 첫번째 숫자를 하한, 두번째 숫자를 상한으로 하는 난수 N을 발생시킨다(예: `$ java 클래스이름 10 10000`인 경우 최소 10, 최대 10000 사이의 난수(양 끝 포함)를 한개 발생시킴)
3. 파일의 첫 줄에 N을 16진수로 출력한다. 출력후 줄바꿈한다.
4. 파일의 두번째 줄부터 N개의 문자열을 다음과 같은 규칙으로 출력한다.
    1. 2이상 10이하 임의의 난수 n을 발생시킨다. n은 단어 개수가 된다.
    2. n개의 단어를 다음 규칙을 따라 만들어서 입력한다.
        1. 1 이상 30 이하의 단어 길이를 난수로 정한다.
        2. 영문 알파벳(`[a-zA-Z]`) 중 하나를 임의로 발생시키는 과정을 1에서 정한 단어 길이만큼 반복한다.
        3. 단어를 저장한다.
        4. n개를 모두 생성했으면 n개를 한 줄에 출력한다. 단어와 단어 사이는 공백(`' '`)으로 구분한다.
**힌트: 난수를 사용해 정규 분포의 난수를 얻는 방법은 `https://boxnwhis.kr/2017/04/13/how_to_make_random_number_generator_for_any_probability_distribution.html` 참조
예를 들어, `java 클래스이름 1 2`를 실행하면 다음과 같은 파일이 생길 수 있다.
```
$ cat test.txt
2
aKjSdka jAjSgkJasdKfjasdJf jAsdfKj aSkdfJka JsdKgjAskj
a b basdkfj aSDkFaJSdFk
```
 */
public class TextDataCreation {
    static final String alphabet = "abcdefghijklnmopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; //알파벳 대소문자 52자

    public static void main(String[] args) throws IOException {
        final int min = Integer.parseInt(args[0]);
        final int max = Integer.parseInt(args[1]);
        int randomNum = ThreadLocalRandom.current().nextInt(min,max+1);

        String fileName= "test.txt";
        writeFile(Paths.get(fileName), randomNum);
    }

    static void writeFile(Path path, int randomNum) throws IOException {
       //파일에 생성할 내용 생성
        List<String> contents = getContents(randomNum);

        //파일에 쓰기
        StandardOpenOption openOption = StandardOpenOption.CREATE;
        Files.write(path, contents, openOption);
    }

    private static List<String> getContents(int randomNum){
        List<String> contents = new ArrayList<>();
        //첫 번째 줄 데이터 생성
        String firstLine = Integer.toHexString(randomNum);
        contents.add(firstLine);

        //두 번째 줄 이후 데이터생성
        int n = ThreadLocalRandom.current().nextInt(2,11); // 2이상 10이하 임의의 난수 n (단어 개수)
        String[] words = new String[n];
        StringBuilder secondLine = new StringBuilder();
        IntStream.range(0,n).forEach(i->{
            int wordLength = ThreadLocalRandom.current().nextInt(1,31); //단어의 길이
            words[i] = makeWord(wordLength);
            secondLine.append(words[i]+" ");
        });

        contents.add(secondLine.toString());

        return contents;
    }

    private static String makeWord(int wordLength){
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0,wordLength).forEach(i->{
            //랜덤한 알파벳 선택
            int randomNum = ThreadLocalRandom.current().nextInt(0,52);
            stringBuilder.append(alphabet.charAt(randomNum));
        });

        return stringBuilder.toString();
    }
}
