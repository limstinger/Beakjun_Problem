import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
	public static int N, M;
	public static ArrayList<Point>[][] data;
	public static int [] dx = {1, 0, -1, 0};
	public static int [] dy = {0, 1, 0, -1};
	static boolean [][] visited;
	static boolean [][] light;
			
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		data = new ArrayList[N+1][N+1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				data[i][j] = new ArrayList<>();
			}
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			data[y][x].add(new Point(a, b));
		}
		
		light = new boolean [N+1][N+1];
		light[1][1] = true;
		
		System.out.println(BFS()+1);

	}
	
	public static int BFS() {
		Queue<Point> q = new LinkedList<>();
		visited = new boolean [N+1][N+1];
		visited = new boolean [N+1][N+1];
		light[1][1] = true;
		q.offer(new Point(1, 1));
		
		int room = 0;
		boolean Switch = false;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(Point p : data[now.y][now.x]) {
				if(!light[p.y][p.x]) {
					Switch = true;
					light[p.y][p.x] = true;
					room++;
				}
			}
						
			for(int j=0;j<4;j++) {
				int nx = now.x + dx[j];
				int ny = now.y + dy[j];
				
				if(nx<1 || nx>N || ny<1 || ny>N) continue;
				
				if(visited[ny][nx]) continue;
				
				if(light[ny][nx]) {
					visited[ny][nx] = true;
					q.offer(new Point(nx, ny));
				}
			}
			
		}
		
		if(Switch) {
			return room + BFS();
		}
		
		return room;
	}
}