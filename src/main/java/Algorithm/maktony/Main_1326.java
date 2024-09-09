/**
문제
개구리가 일렬로 놓여 있는 징검다리 사이를 폴짝폴짝 뛰어다니고 있다. 징검다리에는 숫자가 각각 쓰여 있는데, 
이 개구리는 매우 특이한 개구리여서 어떤 징검다리에서 점프를 할 때는 그 징검다리에 쓰여 있는 수의 배수만큼 
떨어져 있는 곳으로만 갈 수 있다.
이 개구리는 a번째 징검다리에서 b번째 징검다리까지 가려고 한다. 이 개구리가 a번째 징검다리에서 시작하여 
최소 몇 번 점프를 하여 b번째 징검다리까지 갈 수 있는지를 알아보는 프로그램을 작성하시오.

입력
첫째 줄에 징검다리의 개수 N(1≤N≤10,000)이 주어지고, 이어서 각 징검다리에 쓰여 있는 N개의 정수가 주어진다. 
그 다음 줄에는 N보다 작거나 같은 자연수 a, b가 주어지는 데, 이는 개구리가 a번 징검다리에서 시작하여 
b번 징검다리에 가고 싶다는 뜻이다. 징검다리에 쓰여있는 정수는 10,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 개구리가 a번 징검다리에서 b번 징검다리로 최소 몇 번 점프하여 갈 수 있는 지를 출력하시오. 
a에서 b로 갈 수 없는 경우에는 -1을 출력한다.
**/
package Algorithm.maktony;
 
import java.io.*;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1326 { 
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //징검다리 갯수
        int n = Integer.parseInt(br.readLine());
    
        //징검다리에 쓰인 정수 넣을 배열
        int[] bridge= new int[n+1]; 
        
        //징검다리에 쓰인 정수 배열에 잘라넣기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < bridge.length; i++) { 
            bridge[i] = Integer.parseInt(st.nextToken());
        }

        //시작 징검다리, 도착 징검다리 입력받기
        st = new StringTokenizer(br.readLine());
        int startBridge = Integer.parseInt(st.nextToken());
        int endBridge = Integer.parseInt(st.nextToken());

        //입력값 확인
        //System.out.println("\nn = "+n+"\n"+"bridge = "+Arrays.toString(bridge)+"\n"+"startBridge = "+startBridge+"\n"+"endBridge = "+endBridge);

        //버퍼에 입력
        bw.write(new Main_1326().bfs(n, bridge, startBridge, endBridge)+"\n");
        // bw.write(bfs(n, bridge, startBridge, endBridge)+"\n"); //스태틱 안쓰는게 메모리나 시간 조금더 효율적
        //버퍼 출력
        bw.flush();
        //스트림 종료
        bw.close();
        br.close();
        
    }
        
            //BFS
            // static int bfs(int n, int[] bridge, int startBridge, int endBridge) { //스태틱 안쓰는게 메모리나 시간 조금더 효율적
            int bfs(int n, int[] bridge, int startBridge, int endBridge) {
                //방문체크
                boolean[] visited = new boolean[n + 1];
                Queue<int[]> queue = new LinkedList<>();
                
                //시작 지점 큐에 추가 (현재 위치, 점프 횟수)
                queue.add(new int[]{startBridge, 0});
                visited[startBridge] = true;
        
                //BFS 시작
                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    int position = current[0];
                    int jumpCount = current[1];
        
                    //도착지점에 도달하면 점프 횟수를 반환
                    if (position == endBridge) {
                        return jumpCount;
                    }
        
                    //현재 위치에서 점프할 수 있는 범위 계산
                    int jump = bridge[position];
        
                    //왼쪽으로 이동
                    for (int i = position - jump; i >= 1; i -= jump) {
                        if (!visited[i]) {
                            visited[i] = true;
                            queue.add(new int[]{i, jumpCount + 1});
                        }
                    }
                    //오른쪽으로 이동
                    for (int i = position + jump; i <= n; i += jump) {
                        if (!visited[i]) {
                            visited[i] = true;
                            queue.add(new int[]{i, jumpCount + 1});
                        }
                    }
                }
                return -1;
            }
        }