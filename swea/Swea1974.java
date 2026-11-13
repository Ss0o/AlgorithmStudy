import java.util.*;
import java.io.*;

/*
    스도쿠 검증 문제
    각 행, 열, 박스(3x3)을 스도쿠 검증
    입력예시 첫줄에 테스트 케이스 개수, 두번째 줄부터 스도쿠 판 입력
    1
    7 3 6 4 2 9 5 8 1
    5 8 9 1 6 7 3 2 4
    2 1 4 5 8 3 6 9 7
    8 4 7 9 3 6 1 5 2
    1 5 3 8 4 2 9 7 6
    9 6 2 7 5 1 8 4 3
    4 2 1 3 9 8 7 6 5
    3 9 5 6 7 4 2 1 8
    6 7 8 2 1 5 4 3 9
 */
public class Swea1974 {
    static boolean check_box(int[][] arr) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                boolean[] visited = new boolean[10];

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {

                        int num = arr[3*i + k][3*j + l];

                        if(visited[num])
                            return false;

                        visited[num] = true;

                    }
                }
            }
        }

        return true;
    }
    static boolean checkRowAndCol(int[][] arr) {

        for (int i = 0; i < 9; i++) {

            boolean[] rowVisited = new boolean[10];
            boolean[] colVisited = new boolean[10];

            for (int j = 0; j < 9; j++) {

                int rowNum = arr[i][j];
                int colNum = arr[j][i];

                if(rowVisited[rowNum] || colVisited[colNum])
                    return false;

                rowVisited[rowNum] = true;
                colVisited[colNum] = true;

            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {

        int T;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            int[][] arr = new int[9][9];
            int answer = 0;

            for (int i = 0; i < 9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if(check_box(arr) && checkRowAndCol(arr))
                answer = 1;

            System.out.println("#" + test_case + " " + answer);

        }


    }
}
