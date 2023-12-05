import java.util.*;
import java.io.*;

public class Main {
	public static int N, K;
	public static int [][] map;
	public static int result;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int [N][N];
		result = Integer.MAX_VALUE;
 		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 플로이드 마샬 알고리즘
		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		boolean [] visited = new boolean [N];
		visited[K] = true;
		DFS(visited, K, 0, 0);
		
		System.out.println(result);

	}

	public static void DFS(boolean [] visited, int now, int sum, int depth) {
		if(depth==N-1) {
			result = Math.min(result, sum);
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				DFS(visited, i, sum + map[now][i], depth+1);
				visited[i] = false;
			}
		}
		
	}
	
	public static class Planet implements Comparable<Planet>{
		int from;
		int d;
		Planet(int from, int d){
			this.from = from;
			this.d = d;
		}
		
		@Override
		public int compareTo(Planet p) {
			return this.d - p.d;
		}
		
	}
	
}