import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
	public static int [][] map; 
	public static int [][] dist;
	public static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		dist = new int [N][N];
		
		for(int i=0;i<N;i++) {
			String s= br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		BFS();
		System.out.println(dist[N-1][N-1]);
	}
	
	public static void BFS() {
		Queue<Point> q = new LinkedList<>();
		int [] dx = {-1,0,1,0};
		int [] dy = {0, 1, 0, -1};
		
		q.offer(new Point(0, 0));
		dist[0][0] = 0;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			int x = now.x;
			int y = now.y;
			
			for(int i=0;i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny<N && dist[ny][nx]>dist[y][x]) {
					if(map[ny][nx] == 1) {
						dist[ny][nx] = dist[y][x];
					}
					else
						dist[ny][nx] = dist[y][x]+1;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
}