import java.util.*;
import java.io.*;

public class Main {
	public static int N, M;
	public static boolean [][][] construction;
	public static long [][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		construction = new boolean [N+1][M+1][2];
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int Sy = Integer.parseInt(st.nextToken());
			int Sx = Integer.parseInt(st.nextToken());
			int Dy = Integer.parseInt(st.nextToken());
			int Dx = Integer.parseInt(st.nextToken());
			
			if(Sx > Dx) {
				construction[Dy][Dx][0] = true;
			}
			else if(Sy > Dy) {
				construction[Dy][Dx][1] = true;
			}
			else if(Dx > Sx) {
				construction[Sy][Sx][0] = true;
			}
			else if(Dy > Sy) {
				construction[Sy][Sx][1] = true;
			}
		}
		

		dp = new long[N+1][M+1];
		for (int i = 1; i <= M; i++) { // 첫 경로 표시, 갈 수 없을 때까지 1개의 경로를 가짐
			if (construction[0][i-1][0]) break;
			dp[0][i] = 1;
		}
		for (int i = 1; i <= N; i++) { // 첫 경로 표시, 갈 수 없을 때까지 1개의 경로를 가짐
			if(construction[i-1][0][1]) break;
			dp[i][0] = 1;
		}
        for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(!construction[i][j-1][0]) dp[i][j] += dp[i][j-1]; // 왼쪽 도로가 공사중이 아닐 경우
				if(!construction[i-1][j][1]) dp[i][j] += dp[i-1][j]; // 위쪽 도로가 공사중이 아닐 경우
			}
		}
		System.out.println(dp[N][M]);

	}
}