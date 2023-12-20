import java.util.*;
import java.io.*;

public class Main {
	public static int [][] dp;
	public static ArrayList<Integer>[] connect;
	public static boolean [] visited; 

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		connect = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			connect[i] = new ArrayList<>();
		}
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			
			connect[to].add(from);
			connect[from].add(to);
		}
		
		dp = new int [N+1][2];
		visited = new boolean [N+1];
		
		DFS(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	public static void DFS(int start) {
		visited[start] = true;
		
		dp[start][0] = 0;	// 해당 번호가 얼리 어뎁터가 아닌 경우
		dp[start][1] = 1;	// 해당 번호가 얼리 어뎁터가 맞는 경우
		
		for(int from : connect[start]) {
			if(!visited[from]) {
				DFS(from);
				dp[start][0] += dp[from][1];
				dp[start][1] += Math.min(dp[from][0], dp[from][1]);
			}
		}	
	}
}