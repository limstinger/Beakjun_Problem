import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
	public static int N,M;
	public static int [][] map;
	public static int [] dx = {-1, 0, 1, 0};
	public static int [] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		int year = 0;
	
		while((cnt=CalculateMass()) < 2) {
			if(cnt==0) {
				year=0;
				break;
			}
		
			Melt();
			year++;
		}
		
		System.out.println(year);
	}
	
	public static int CalculateMass() {
		boolean [][] visited = new boolean [N][M];
		int cnt = 0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0 && !visited[i][j]) {
					DFS(j, i, visited);
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	public static void DFS(int x, int y, boolean [][] visited) {
		visited[y][x] = true;
		
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=M || ny<0 || ny>=N) continue;
			
			if(map[ny][nx]!=0 && !visited[ny][nx]) {
				DFS(nx, ny, visited);
			}
		}
	}
	
	// 빙하를 녹이는 함수
	public static void Melt() {
		Queue<Point> q = new LinkedList<>();
		
		boolean [][] visited = new boolean [N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0) {
					q.offer(new Point(j, i));
					visited[i][j] = true;				}
			}
		}
		
		while(!q.isEmpty()) {
			Point Ice = q.poll();
			
			int seaArea = 0;
			
			for(int i=0;i<4;i++) {
				int nx = Ice.x + dx[i];
				int ny = Ice.y + dy[i];
				
				if(nx<0 || nx>=M || ny<0 || ny>=N) continue;
				
				if(map[ny][nx]==0 && !visited[ny][nx]) {
					seaArea++;
				}
			}
			
			if(map[Ice.y][Ice.x] - seaArea < 0) {
				map[Ice.y][Ice.x] = 0; 
			}
			else {
				map[Ice.y][Ice.x] -= seaArea;
			}
		}
	}
}