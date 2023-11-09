import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static Queue<DeathKnight> q;
	public static int x_1, x_2, y_1, y_2;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		// (x_1, y_1):데스나이트의 현위치, (x_2, y_2):도착해야 할 위치
		x_1 = Integer.parseInt(st.nextToken());
		y_1 = Integer.parseInt(st.nextToken());
		x_2 = Integer.parseInt(st.nextToken());
		y_2 = Integer.parseInt(st.nextToken());
			
		BFS();
		
	}
	
	public static void BFS() {
		q = new LinkedList<>();
		int [] dx = {-2, -2, 0, 0, 2, 2};
		int [] dy = {-1, 1, -2, 2,-1, 1};
		
		int [][] map = new int [N][N];
		for(int i=0;i<N;i++) {
			Arrays.fill(map[i], 400);
		}
		map[y_1][x_1] = 0;
		q.offer(new DeathKnight(x_1, y_1, 0));
		int min_moved = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			DeathKnight now = q.poll();
			int x = now.x;
			int y = now.y;
			for(int i=0;i<6;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				
				if(nx==x_2 && ny==y_2) {
					min_moved = Math.min(min_moved, now.move+1);
				}
				
				else {
					if(map[ny][nx] > now.move+1) {
						q.offer(new DeathKnight(nx, ny, now.move+1));
						map[ny][nx] = now.move+1;
					}
				}
							
			}
		}
		
		if(min_moved==Integer.MAX_VALUE){
			System.out.println(-1);
		}
		else {
			System.out.println(min_moved);
		}
	}
	public static class DeathKnight{
		int x;
		int y;
		int move;
		DeathKnight(int x, int y, int move){
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
}