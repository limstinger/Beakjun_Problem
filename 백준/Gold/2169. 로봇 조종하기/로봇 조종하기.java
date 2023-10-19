import java.util.*;
import java.io.*;

public class Main {
	public static int N ,M;
	public static int [][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N+1][M+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DP();
		
	}
	
	public static void DP() {
		int [][] dp = new int [N+1][M+1]; 
		int [][] temp = new int [2][M+2];
		
		dp[1][1] = map[1][1];
		
		for(int i=2;i<=M;i++) {
			dp[1][i] = dp[1][i-1] + map[1][i];
		}
		
		for(int i=2;i<=N;i++) {
			
			temp[0][0] = dp[i-1][1];
			for(int j=1;j<=M;j++) {	// 왼쪽에서 시작
				temp[0][j] =  Math.max(temp[0][j-1], dp[i-1][j]) + map[i][j];
			}
			
			temp[1][M+1] = dp[i-1][M];
			for(int j=M;j>=1;j--) {	// 오른쪽에서 시작
				temp[1][j] = Math.max(temp[1][j+1], dp[i-1][j]) + map[i][j];
			}
			
			for(int j=1;j<=M;j++) {
				dp[i][j] = Math.max(temp[0][j], temp[1][j]);
			}
		}
		
		/*for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}*/
		
		System.out.println(dp[N][M]);
	}

}
