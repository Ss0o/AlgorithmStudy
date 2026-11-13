import java.util.*;
import java.io.*;

public class Swea1244 {

    /*
        최대 상금 문제
        가장 첫 줄은 전체 테스트 케이스의 수이다.

        최대 10개의 테스트 케이스가 표준 입력을 통하여 주어진다.

        각 테스트 케이스에는 숫자판의 정보와 교환 횟수가 주어진다.

        숫자판의 정보는 정수형 숫자로 주어지고 최대 자릿수는 6자리이며, 최대 교환 횟수는 10번이다.

        123의 경우

        입력 예시
        3
        123 1
        2737 1
        32888 2

        출력 예시
        #1 321
        #2 7732
        #3 88832



        사고 흐름
        두 개를 바꿔가나가며 진행
                                   123
       1번       213                132                 321

       2번  123 231 312         312 123 231          231 312 123

        중복된 숫자들이 나옴 -> 어떻게 기억할까? -> 중복확인만 하면 되니깐 Set
        + 줄마다 따로 기억해야 하니깐 visited[depth]

        마지막 줄(maxSwap == depth)일 때 뭘 해야 하지?
        -> 그 줄의 숫자들 중 최대값이 정답

     */
    static char[] nums;
    static int maxSwap;
    static int answer;
    static Set<String>[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 123 1 로 입력하니깐 123은 st.nextToken()으로 받고 그걸 toCharArray로 char배열로 변환
            nums = st.nextToken().toCharArray();
            maxSwap = Integer.parseInt(st.nextToken());
            answer = 0;

            visited = new HashSet[maxSwap + 1];
            for (int i = 0; i <= maxSwap; i++) {
                visited[i] = new HashSet<>();
            }
            dfs(0);
            System.out.println("#" + test_case + " " + answer);

        }

    }

    /*
        dfs(0) 123
        │
        ├ 213 (depth 1)  처음 → 탐색
        │   ├ 123 (depth 2)  처음 → 탐색 → 아래로 213, 321, 132 (depth 3)
        │   ├ 312 (depth 2)  처음 → 탐색 → 아래로 132, 213, 321 (depth 3)
        │   └ 231 (depth 2)  처음 → 탐색 → 아래로 321, 132, 213 (depth 3)
        │
        ├ 321 (depth 1)  처음 → 탐색
        │   ├ 231 (depth 2)   이미 봄 → return
        │   ├ 123 (depth 2)   이미 봄 → return
        │   └ 312 (depth 2)   이미 봄 → return
        │
        └ 132 (depth 1)  처음 → 탐색
            ├ 312 (depth 2)   이미 봄 → return
            ├ 231 (depth 2)   이미 봄 → return
            └ 123 (depth 2)   이미 봄 → return
     */
    static void dfs(int depth) {
        String cur = new String(nums);
        if(!visited[depth].add(cur)) return;

        if(depth == maxSwap)
        {
            answer = Math.max(answer, Integer.parseInt(cur));
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                swap(i, j);
                dfs(depth + 1);
                swap(i, j);
            }
        }
    }

    static void swap(int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}