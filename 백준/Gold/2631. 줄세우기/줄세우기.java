import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int [] student;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		student = new int [N+1];
		
		for(int i=1;i<=N;i++) {
			student[i] = Integer.parseInt(br.readLine()); 
		}
		
		int [] dp = new int [N+1];
		dp[1] = 1;
		int ans = 0;
		for(int i=2;i<=N;i++) {
			dp[i] = 1;
			for(int j=1;j<=i;j++) {
				if(student[i] > student[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(N-ans);
	}

}
