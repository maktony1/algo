/**
 * 2024-09-21 토 13번째문제 // 어두운 굴다리 // 이분탐색
 문제
 인하대학교 후문 뒤쪽에는 어두운 굴다리가 있다. 겁쟁이 상빈이는 길이 조금이라도 어둡다면 가지 않는다.
 따라서 굴다리로 가면 최단거리로 집까지 갈수 있지만, 굴다리는 어둡기 때문에 빙빙 돌아서 집으로 간다.
 안타깝게 여긴 인식이는 굴다리 모든 길 0~N을 밝히게 가로등을 설치해 달라고 인천광역시에 민원을 넣었다.
 인천광역시에서 가로등을 설치할 개수 M과 각 가로등의 위치 x들의 결정을 끝냈다.
 그리고 각 가로등은 높이만큼 주위를 비출 수 있다. 하지만 갑자기 예산이 부족해진 인천광역시는 가로등의
 높이가 높을수록 가격이 비싸지기 때문에 최소한의 높이로 굴다리 모든 길 0~N을 밝히고자 한다.
 최소한의 예산이 들 높이를 구하자. 단 가로등은 모두 높이가 같아야 하고, 정수이다.

 다음 그림을 보자.

 가로등의 높이가 H라면 왼쪽으로 H, 오른쪽으로 H만큼 주위를 비춘다.
 다음 그림은 예제1에 대한 설명이다.

 가로등의 높이가 1일 경우 0~1사이의 길이 어둡기 때문에 상빈이는 지나가지 못한다.
 아래 그림처럼 높이가 2일 경우 0~5의 모든 길이 밝기 때문에 상빈이는 지나갈 수 있다.

 입력
 첫 번째 줄에 굴다리의 길이 N 이 주어진다. (1 ≤ N ≤ 100,000)
 두 번째 줄에 가로등의 개수 M 이 주어진다. (1 ≤ M ≤ N)
 다음 줄에 M 개의 설치할 수 있는 가로등의 위치 x 가 주어진다. (0 ≤ x ≤ N)
 가로등의 위치 x는 오름차순으로 입력받으며 가로등의 위치는 중복되지 않으며, 정수이다.

 출력
 굴다리의 길이 N을 모두 비추기 위한 가로등의 최소 높이를 출력한다.
*/
package Algorithm.maktony.solved;

import java.io.*;

public class Main_13_17266_20240921 {

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
    