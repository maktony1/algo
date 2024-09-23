/**
 * 2024-09-10 화 2번째문제 // 섬의 개수 // BFS/DFS
문제
정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.

한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 
두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 
지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. 
w와 h는 50보다 작거나 같은 양의 정수이다.

둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.

입력의 마지막 줄에는 0이 두 개 주어진다.

출력
각 테스트 케이스에 대해서, 섬의 개수를 출력한다.

**/
package Algorithm.maktony.solved;
  
import java.io.*;
import java.util.*;

public class Main_4963_2_20240910 {
     // 8방향으로 이동하기 위한 배열
     static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};  // 대각선, 상하좌우
     static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};  // 대각선, 상하좌우
     static int w, h;
     static int[][] map;
     static boolean[][] visited;
 
     public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         while (true) {
             // 너비(w)와 높이(h) 입력 받기
             StringTokenizer st = new StringTokenizer(br.readLine());
             w = Integer.parseInt(st.nextToken());
             h = Integer.parseInt(st.nextToken());
             
             if (w == 0 && h == 0) break;  // 입력의 끝
             
             // 지도 입력 받기
             map = new int[h][w];
             visited = new boolean[h][w];
             for (int i = 0; i < h; i++) {
                 st = new StringTokenizer(br.readLine());
                 for (int j = 0; j < w; j++) {
                     map[i][j] = Integer.parseInt(st.nextToken());
                 }
             }
 
             int islandCount = 0;
             // 지도를 순차적으로 탐색
             for (int i = 0; i < h; i++) {
                 for (int j = 0; j < w; j++) {
                     // 아직 방문하지 않은 땅(1)이 있으면 BFS/DFS 실행
                     if (map[i][j] == 1 && !visited[i][j]) {
                         bfs(i, j);  // 또는 dfs(i, j);
                         islandCount++;  // 섬 개수 증가
                     }
                 }
             }
             
             // 결과 출력
             System.out.println(islandCount);
         }
         br.close();
     }
 
     // BFS로 섬 탐색
     static void bfs(int x, int y) {
         Queue<int[]> queue = new LinkedList<>();
         queue.add(new int[] {x, y});
         visited[x][y] = true;
 
         while (!queue.isEmpty()) {
             int[] current = queue.poll();
             int curX = current[0];
             int curY = current[1];
 
             // 8방향으로 이동하면서 연결된 땅 탐색
             for (int i = 0; i < 8; i++) {
                 int newX = curX + dx[i];
                 int newY = curY + dy[i];
 
                 // 지도 범위 안에 있고, 아직 방문하지 않았으며 땅인 경우
                 if (newX >= 0 && newY >= 0 && newX < h && newY < w && !visited[newX][newY] && map[newX][newY] == 1) {
                     queue.add(new int[] {newX, newY});
                     visited[newX][newY] = true;
                 }
             }
         }
     }
 }