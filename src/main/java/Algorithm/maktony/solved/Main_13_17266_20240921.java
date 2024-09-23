/**
 * 2024-09-21 토 13번째문제 // 어두운 굴다리 // 이분탐색
 
*/
package Algorithm.maktony.solved;

import java.io.*;

public class Main_17266_13_20240921 {

    public static void main(String[] args) throws IOException {
               // 입력을 위한 BufferedReader 설정
               BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
               BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       
               // 굴다리 길이와 가로등 개수 입력
               int N = Integer.parseInt(br.readLine());
               int M = Integer.parseInt(br.readLine());
       
               // 가로등 위치 입력
               int[] lights = new int[M];
               String[] input = br.readLine().split(" ");
               for (int i = 0; i < M; i++) {
                   lights[i] = Integer.parseInt(input[i]);
               }
       
               // 이분 탐색을 위한 변수
               int left = 1;
               int right = N;
               int result = N;
       
               // 이분 탐색 시작
               while (left <= right) {
                   int mid = (left + right) / 2;
       
                   // 해당 높이로 모든 길을 밝힐 수 있는지 확인
                   if (canIlluminateAll(lights, N, mid)) {
                       result = mid;  // 가능하다면, 결과로 저장하고 높이를 줄여본다.
                       right = mid - 1;
                   } else {
                       left = mid + 1;  // 불가능하다면, 높이를 늘린다.
                   }
               }
       
               // 결과 출력
               bw.write(result + "\n");
               bw.flush();
               bw.close();
               br.close();
           }
       
           // 가로등의 높이로 굴다리 전체를 비출 수 있는지 확인하는 함수
           public static boolean canIlluminateAll(int[] lights, int N, int height) {
               int lastIlluminated = 0;
       
               // 가로등을 하나씩 확인하며 비추는 구간을 확인
               for (int light : lights) {
                   // 가로등이 비추는 범위는 [light - height, light + height]
                   if (light - height > lastIlluminated) {
                       return false;  // 중간에 비추지 못한 구간이 있으면 false 반환
                   }
                   lastIlluminated = light + height;  // 비추는 마지막 위치 갱신
               }
       
               // 마지막으로 비춘 위치가 굴다리의 끝까지 닿는지 확인
               return lastIlluminated >= N;
           }
       }
    