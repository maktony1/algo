package Algorithm.maktony.pending;

import java.util.*;
import java.io.*;

public class Main_1145_my {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int min = 10000;
        int[] arr = new int[5];

        for (int i = 0; i < 5; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 5; j++) {
                for (int k = j + 1; k < 5; k++) {
                    if ((min > arr[i] || min > arr[j] || min > arr[k]) && ( ((arr[k]%arr[i])==0) && ((arr[k]%arr[j])==0) ) ){
                        if((arr[i]*arr[k])%arr[j]==0){
                            min=arr[i]*arr[k];
                        }
                    }
                }
            }
        }
        System.out.println(min);
        System.out.println(Arrays.toString(arr));
    }
}
