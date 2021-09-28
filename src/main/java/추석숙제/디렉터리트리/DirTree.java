package 추석숙제.디렉터리트리;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
# 문제 1: 디렉터리 트리
현재 디렉터리를 기준으로 디렉터리 트리를 구성해주는 명령줄 프로그램을 만들라(파일 이름은 출력하지 않고 디렉터리 이름만 출력한다).
디렉터리 방문은 DFS(깊이우선)를 사용하고, 깊이가 깊어서 스택 오버플로가 나는 경우를 방지하기 위해서 Deque 인터페이스를 제공하는 컬렉션을 스택처럼 사용하면서 디렉터리를 처리하라.

예: (최상위에 A1등의 디렉터리들이 아래 깊이에 B1등이, 세번째 깊이에 C1등이 있다고 가정)
$ java tree
CWD: C:\TEMP
+─ A1
   +- B1
      +- C1
   +─ B2
   +- B3
+- A2
 */
public class DirTree {
    public static void main(String[] args) throws IOException {
        //현재 디렉터리 가져오기
        String currentDirName = System.getProperty("user.dir");
        Path currentDir = Paths.get(currentDirName);

        makeDirTree(currentDir);
    }

    private static void makeDirTree(Path currentDir) throws IOException {
        //현재 디렉토리의 하위 디렉토리와 파일 정보를 저장
        Stream<Path> childPathStream = Files.walk(currentDir);
        Files.walk(currentDir).forEach(x->System.out.println(x));
        List<Path> childPathList = childPathStream.collect(Collectors.toList());  //스트림 to List

        //탐색을 위한 노드 연결 그래프 생성
        List<List<Integer>> pathGraph = new ArrayList<>(childPathList.size());
        IntStream.range(0,childPathList.size()).forEach(x->pathGraph.add(new ArrayList<>()));
        for(int i=0;i<childPathList.size();i++)
            for(int j=0;j<childPathList.size();j++){
                if(i==j) continue;

                if(childPathList.get(i).equals(childPathList.get(j).getParent())){
                    System.out.println(i+"에 저장:"+childPathList.get(j));
                    pathGraph.get(i).add(j);
                }
            }

        //리스트 위치별 방문 여부를 나타내는 배열 생성
        boolean [] visited = new boolean[childPathList.size()];
        IntStream.range(0,childPathList.size()).forEach(i -> visited[i]=false);

        // dfs 탐색을 위한 스택 생성
        Deque<Integer> stack = new LinkedList<>();
        // 첫 노드 방문
        stack.push(0);
        visited[0] = true;

        StringBuilder prefix = new StringBuilder("+─"); //트리 구조를 나타내기 위한 접두어

        //스택에 원소가 없을 때 까지 반복
        while(!stack.isEmpty()){
            int now = stack.peek();
            boolean hasNearNode = false;

            //인접한 노드를 방문하지 않았을 경우 스택에 넣고 방문처리
            for(Integer i: pathGraph.get(now)){
                if(!visited[i]){
                    hasNearNode = true;
                    stack.push(i);
                    visited[i] = true;
                    System.out.println(prefix.toString()+childPathList.get(i).getFileName());
                    prefix.insert(0,"   ");
                    break;
                }
            }

            //반문하지 않은 인접 노드가 없는 경우 해당 노드 꺼내기
            if(hasNearNode==false){
                prefix.delete(0,3);
                stack.pop();
            }
        }
    }
}
