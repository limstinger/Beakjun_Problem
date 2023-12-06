import java.util.*;
import java.io.*;

public class Main {
	public static char [][] map;
	public static int [][] dist;
	public static int W, H;
	public static int [] dx = {-1, 0, 1, 0};
	public static int [] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());	// 가로 길이
		H = Integer.parseInt(st.nextToken());	// 세로 길이
		
		map = new char [H][W];
		dist = new int [H][W];
		
		int start_x = -1; int start_y = -1;
		int end_x = -1; int end_y = -1;
		for(int i=0;i<H;i++) {
			String s = br.readLine();
			for(int j=0;j<W;j++) {
				char c = s.charAt(j);
				
				map[i][j] = c;
				
				if(c=='T') {		// 현재 위치
					start_x = j;
					start_y = i;
				}
				else if(c=='E') {	// 탈출구에 도달했을 시
					end_x = j;
					end_y = i;
				}
			}
		}
	
		for(int i=0;i<H;i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		BFS(start_x, start_y);
	
		if(dist[end_y][end_x]==Integer.MAX_VALUE) {
			System.out.println("-1");
		}
		else {
			System.out.println(dist[end_y][end_x]);
		}
		
	}
	
	public static void BFS(int start_x, int start_y) {
		PriorityQueue<Position> pq = new PriorityQueue<>();
		boolean [][] visited = new boolean [H][W];
		
		pq.offer(new Position(start_x, start_y, 0));
		
		dist[start_y][start_x] = 0;
		
		while(!pq.isEmpty()) {
			Position now = pq.poll();
			
			if(visited[now.y][now.x]) continue;
			visited[now.y][now.x] = true;
			
			for(int i=0;i<4;i++) {
				int go_cnt = can_go(i, now.x, now.y);
				
				if(go_cnt < 1) continue;
				int sum_dist = 0;
				for(int j=1;j<=go_cnt;j++) {
					if(map[now.y + dy[i]*j][now.x + dx[i]*j]=='E') continue;
					else {
						sum_dist += (map[now.y + dy[i]*j][now.x + dx[i]*j] - '0');
					}
				}
				
				int nx = now.x + dx[i]*go_cnt;
				int ny = now.y + dy[i]*go_cnt;
				
				if(dist[ny][nx] > sum_dist + now.time) {
					dist[ny][nx] = now.time + sum_dist;
					pq.offer(new Position(nx, ny, dist[ny][nx]));
				}
				
			}
		}
		
	}
	
	public static int can_go(int dir, int x, int y) {
		int cnt = 1;
		while(true) {
			int nx = x + dx[dir] * cnt;
			int ny = y + dy[dir] * cnt;
			
			if(nx<0 || nx>=W || ny<0 || ny>=H) break;
			if(map[ny][nx]=='H') break;		// 구멍이라면
			if(map[ny][nx]=='R') return cnt-1;
			if(map[ny][nx]=='E') return cnt;
			
			cnt++;
		}
		return -1;
	}
	
	public static class Position implements Comparable<Position>{
		int x;
		int y;
		int time;
		Position(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		@Override
		public int compareTo(Position p) {
			return this.time - p.time;
		}
	}

}