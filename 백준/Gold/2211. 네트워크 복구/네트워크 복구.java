import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
	public static int N;
	public static int INF = 987654321;
	public static int [] path;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 컴퓨터 번호 수
		int M = Integer.parseInt(st.nextToken());	// 회선의 정보 수
		
		List<Circuit> computer[] = new ArrayList[N+1];
		path = new int [N+1];
		
		for(int i=1;i<=N;i++) {
			computer[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	// A 컴퓨터
			int B = Integer.parseInt(st.nextToken());	// B 컴퓨터
			int C = Integer.parseInt(st.nextToken());	// 통신 시간
			
			computer[A].add(new Circuit(B, C));
			computer[B].add(new Circuit(A, C));
		}
		
		for(int i=1;i<=N;i++) {
			Collections.sort(computer[i]);
		}
		
		Algorithm(computer);

		System.out.println(N-1);
		for(int i=2;i<=N;i++) {
			System.out.println(i+" "+path[i]);
		}
	}
	
	public static void Algorithm(List<Circuit>[] computer) {
		PriorityQueue<Circuit> pq = new PriorityQueue<>();
		List<Point> circuit = new ArrayList<>();
		int [] dist = new int [N+1];
		
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		pq.offer(new Circuit(1, 0));
		
		while(!pq.isEmpty()) {
			Circuit now = pq.poll();
			for(Circuit c : computer[now.from]) {
				int cost = now.c + c.c;
				if(dist[c.from] > cost) {
					dist[c.from] = cost;
					path[c.from] = now.from;
					pq.add(new Circuit(c.from, cost));
				}
			}
		}
	}
	
	
	public static class Circuit implements Comparable<Circuit>{
		int from;
		int c;
		
		Circuit(int from, int c){
			this.from = from;
			this.c = c;
		}
		
		@Override
		public int compareTo(Circuit c) {
			return this.c - c.c;
		}
	}
}