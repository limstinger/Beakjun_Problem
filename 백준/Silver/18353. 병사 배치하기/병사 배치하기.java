import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int [] map = new int [N];
		int [] dp = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=N-1;i>=0;i--) {
			map[i] = Integer.parseInt(st.nextToken());
			dp[i] =1;
		}
		
		
		
		/*System.out.print("\n");
		for(int i=0;i<N;i++) {
			System.out.print(map[i]+" ");
		}*/
		
		for(int i=1;i<N;i++) {
			for(int j=0;j<i;j++) {
				if(map[i] > map[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		/*for(int i=0;i<N;i++) {
			System.out.print(dp[i]+" ");
		}*/
		
		int max = dp[0];
		for(int i=1;i<N;i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(N-max);
		
	}
}