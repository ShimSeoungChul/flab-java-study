package 추석숙제.텍스트데이터생성과통계;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
# 문제 3: 텍스트 데이터 통계
문제 2에서 생성한 형식의 파일을 읽어서 통계를 계산하는 프로그램 `Stat.java`를 작성하라. 출력은 다음을 표시해야 한다.
`[전체 줄수]`와 같은 표현은 실제 텍스트가 아니라 해당 위치에 표시할 정수값에 대한 설명이다.
```
$ java Stat test.txt
라인수:[전체 줄수]
전체 단어 수:[전체 단어 수]
전체 글자 수:[공백은 제외한 모든 단어 길이의 합]
가장 긴 단어 길이:[가장 긴 단어 길이]
가장 긴 단어:[가장 긴 단어. 둘 이상이면 공백으로 구분해 모두 출력]
가장 잛은 단어 길이:[가장 짧은 단어 길이]
가장 짧은 단어:[가장 짧은 단어. 둘 이상이면 공백으로 구분해 모두 출력]
글자 도수분포: a:[a빈도수] b:[b빈도수] c:[c빈도수] ... Z:[Z빈도수]
단어 길이 도수분표: 1:[길이가1인단어빈도수] .... 30:[길이가30인단어빈도수]
```
여기서 `:`와 숫자 사이에는 공백이 **없어야**한다.
1. 문자열을 리스트 등의 컬렉션에 넣고 처리하는 방식으로 구현하라.
2. 파일을 한줄씩 읽어서 스트림에 넘겨주는 스트림 생성기를 만들고, 스트림 collect를 사용해 통계를 계산하라.
 스트림을 여러번 재방문하지 말고, 원하는 모든 통계를 업데이트해주는 collector 클래스를 만들고 이를 사용하라.
3. 파일을 리스트에 읽고 이를 병렬 스트림으로 만들어서 병렬처리가 가능하게 하라.
 */
public class TextDataStatistics {
    public static void main(String[] args) throws IOException {
        final String fileName = args[0];
        print(fileName);
    }

    private static void print(String fileName) throws IOException {
        //생성할 데이터들 선언
        int  lineNum = 0, wordNum=0, charNum=0;
        String longestWord="", shortestWord="";
        StringBuilder charFrequencyDistribution = new StringBuilder();
        StringBuilder wordFrequencyDistribution = new StringBuilder();


        //파일 읽기
        BufferedReader bufReader = new BufferedReader(new FileReader(new File(fileName)));
        String line = "";
        while((line = bufReader.readLine()) != null){
            lineNum ++;
            if(lineNum == 2){
                List<String> wordList = makeWord(line);
                wordNum = wordList.size();
                charNum = getCharNum(wordList);
                longestWord = getLongestWord(wordList);
                shortestWord = getShortestWord(wordList);
            }
        }

        System.out.println("라인수:" + lineNum);
        System.out.println("전체 단어 수:"+wordNum);
        System.out.println("전체 글자 수:" + charNum);
        System.out.println("가장 긴 단어 길이:"+longestWord.length());
        System.out.println("가장 긴 단어:"+longestWord);
        System.out.println("가장 짧은 단어 길이:"+shortestWord.length());
        System.out.println("가장 짧은 단어:"+shortestWord);
//        System.out.println("글자 도수분포:"+charFrequencyDistribution.toString());
//        System.out.println("단어 길이 도수분표:"+wordFrequencyDistribution.toString());
    }

    private static List<String> makeWord(String line){
        return Arrays.asList(line.split(" "));
    }

    private static int getCharNum(List<String> wordList){
        return wordList.stream()
                .collect(Collectors.joining("")) //스트림 하나로 합치기
                .length();
    }

    private static String getLongestWord(List<String> wordStream){
        Comparator<String> compareByLength = (s1, s2) -> s2.length() - s1.length();
        return wordStream.stream().sorted(compareByLength)
                .findFirst()
                .get();
    }
    private static String getShortestWord(List<String> wordStream){
        Comparator<String> compareByLength = (s1, s2) -> s1.length() - s2.length();
        return wordStream.stream().sorted(compareByLength)
                .findFirst()
                .get();
    }
}
