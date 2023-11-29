import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int INF = 987654321;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<TC;i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 지점의 개수
			int M = Integer.parseInt(st.nextToken());	// 도로의 개수
			int W = Integer.parseInt(st.nextToken());	// 웜홀의 개수
			
			ArrayList<Road>[] road = new ArrayList[N+1];
			
			for(int j=1;j<=N;j++) {
				road[j] = new ArrayList<>();
			}
			
			// 도로 입력
			for(int j=0;j<M+W;j++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				// 도로는 양방향이므로
				if(j < M) {
					road[to].add(new Road(from, T));
					road[from].add(new Road(to, T));
				}
				else {
					// 웜홀은 일방향
					road[to].add(new Road(from, -T));		// 웜홀에서는 시간이 거꾸로 가므로
				}
			}
			
			if(bellmanFord(road)) {
				sb.append("YES\n");
			}
			else {
				sb.append("NO\n");
			}
			
		}
		
		System.out.println(sb);

	}
	
	public static boolean bellmanFord(ArrayList<Road>[] road) {
		int [] dist = new int [N+1];
		Arrays.fill(dist, INF);
		boolean update = false;
		
		dist[1] = 0;
		
		for(int i=1;i<N;i++) {
			update = false;
			
			for(int j=1;j<=N;j++) {
				for(Road r : road[j]) {
					if(dist[r.from] > dist[j] + r.T) {
						dist[r.from] = dist[j] + r.T;
						update = true;
					}
				}
			}
			if(!update) {
				break;
			}
		}
		
		if(update) {
			for(int i=1;i<=N;i++) {
				for(Road r : road[i]) {
					if(dist[r.from] > dist[i] + r.T) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static class Road{
		int from;
		int T;
		Road(int from, int T){
			this.from =from;
			this.T = T;
		}
	}

}
