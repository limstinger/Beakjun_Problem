import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int [][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int [N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		long [][][] dp = new long [N+1][N+1][4];	// 1-가로 2-세로 3-대각선
		dp[1][2][1] = 1;
		long result = 0;
			
		for(int i=1;i<=N;i++) {
			for(int j=2;j<=N;j++) {
				for(int k=1;k<4;k++) {	// 파이프 방향
					if(i==N && j==N) {
						result+=dp[N][N][k];
					}
					else {
						if(k==1) {	// 이전에 둔 파이프가 가로일 때
							if(can_pipe(i, j, 1)) {	// 가로 파이프를 둘 수 있을 때
								dp[i][j+1][1] +=dp[i][j][1];
							}
							if(can_pipe(i, j, 3)) {	// 대각선 파이프를 둘 수 있을 때
								dp[i+1][j+1][3] += dp[i][j][1];
							}
						}
						else if(k==2) {	// 이전에 둔 파이프가 세로일 때
							if(can_pipe(i, j, k)) {
								if(can_pipe(i, j, 2)) {	// 세로 파이프를 둘 수 있을 때
									dp[i+1][j][2] += dp[i][j][2];
								}
								if(can_pipe(i, j, 3)) {	// 대각선 파이프를 둘 수 있을 때
									dp[i+1][j+1][3] += dp[i][j][2];
								}
							}
						}
						else if(k==3) {	// 이전에 둔 파이프가 대각선일 때
							if(can_pipe(i, j, 1)) {	// 가로 파이프를 둘 수 있을 때
								dp[i][j+1][1] += dp[i][j][3];
							}
							if(can_pipe(i, j, 2)) {	// 세로 파이프를 둘 수 있을 때
								dp[i+1][j][2] += dp[i][j][3];
							}
							if(can_pipe(i, j, 3)) {	// 대각선 파이프를 둘 수 있을 때
								dp[i+1][j+1][3] += dp[i][j][3];
							}
						}
					}
				}
			}
		}
		
		System.out.println(result);
			
	}
	
	public static boolean can_pipe(int y, int x,int dir) {
		if(dir==1) {	// 가로
			if(x>=N || map[y][x+1] ==1) {
				return false;
			}
		}
		else if(dir==2) {	// 세로
			if(y>=N || map[y+1][x]==1) {
				return false;
			}
		}
		else if(dir==3) {	// 대각선
			if((x>=N || y>=N) || map[y+1][x]==1 || map[y][x+1]==1 || map[y+1][x+1]==1) {
				return false;
			}
		}
		return true;
	}
}