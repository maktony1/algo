package Algorithm.maktony.solving;

import java.util.*;
import java.io.*;

public class Main_1145 {
    public static void main(String[] args) throws IOException{
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int min = 100;
        int[] arr = new int[5];

        for (int i = 0; i < 5; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < arr.length; i++) {
            if(min >= arr[i]){
                
            }
        }
        System.out.println(Arrays.toString(arr));
    }    
}
