package com.algorithm.study.swea;

import java.util.*;
import java.io.*;

/*
    스도쿠 검증 문제
    각 행, 열, 박스(3x3)을 스도쿠 검증
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
