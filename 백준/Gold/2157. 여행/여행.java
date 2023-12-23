import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// N개의 도시
		int M = Integer.parseInt(st.nextToken());	// M개의 도시이하로 가야함
		int K = Integer.parseInt(st.nextToken());
		
		int [][] r = new int [N+1][N+1];
		
		ArrayList<Airplane>[] road = new ArrayList[N+1];
		
		for(int i=1;i<=N;i++) {
			road[i] = new ArrayList<>();
		}
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if(to>=from) continue; 
			road[to].add(new Airplane(from, v));
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		int [][] dp = new int [M+1][N+1];
		
		int result = 0;
		int cnt = 1;
		q.offer(1);
		while(!q.isEmpty() && cnt < M) {
			int size = q.size();
			
			while(size-->0) {
				int now = q.poll();
				
				for(Airplane a : road[now]) {
					int nextCity = a.from;
					int nextScore = a.v + dp[cnt][now];
					
					if(dp[cnt+1][nextCity] >= nextScore) {
						continue;
					}
					
					dp[cnt+1][nextCity] = nextScore;
					q.offer(nextCity);
				}
			}
			
			cnt++;
		}
		
		for(int i=1;i<=M;i++) {
			result = Math.max(result, dp[i][N]);
		}
		System.out.println(result);
	}
	
	public static class Airplane implements Comparable<Airplane>{
		int from;
		int v;
		
		Airplane(int from, int v){
			this.from = from;
			this.v = v;
		}
		
		@Override
		public int compareTo(Airplane a) {
			return a.v - this.v;
		}
	}
}