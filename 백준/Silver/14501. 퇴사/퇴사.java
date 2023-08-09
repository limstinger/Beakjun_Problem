import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int [][] work;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		work = new int [N][2];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<2;j++) {
				work[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		find();
	}
	
	public static void find() {
		int [] dp = new int [N+1];
		
		//점화식
		//현재 날짜에서 소요 시간과 비용을 더해 dp에 저장한다.
		//이후, 중복될 때 최대값을 넣는다.
		// dp[i+현재 추가할 상담 일수] = max(dp[i+현재 추가할 상담 일수], dp[i] + 현재 추가할 상담의 가치)
		for(int i=0;i<N;i++) {
			if(i+work[i][0] <=N) {
				dp[i+work[i][0]] = Math.max(dp[i+work[i][0]], dp[i] + work[i][1]);
			}
			
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
	
	
	System.out.println(dp[N]);
	}
}
