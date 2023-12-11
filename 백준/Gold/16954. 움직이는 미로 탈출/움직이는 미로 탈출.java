import java.util.*;
import java.io.*;

public class Main {
	public static int [] dx = {0, 1, 1, 1, 0, -1, -1, -1, 0};
	public static int [] dy = {0, 1, 0, -1, -1, -1, 0, 1, 1};
  	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int [][] map = new int [8][8];
		
		for(int i=0;i<8;i++) {
			String s =  br.readLine();
			for(int j=0;j<8;j++) {
				char c = s.charAt(j);
				if(c=='.') {
					map[i][j] = 0;
				}
				else {
					map[i][j] = 1;
				}
			}
		}
		
		if(BFS(map)) {
			System.out.println("1");
		}
		else
			System.out.println("0");

	}
	
	public static boolean BFS(int [][] map) {
		Queue<Node> q = new LinkedList<>();
		boolean [][] visited;
		
		q.offer(new Node(0, 7));
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			visited = new boolean [8][8];
			
			for(int i=0;i<size;i++) {
				Node now = q.poll();
				
				if(map[now.y][now.x]==1) {
					continue;
				}
				
				if(now.x == 7 && now.y==0) {
					return true;
				}
				
				for(int k=0;k<9;k++) {
					int nx = now.x + dx[k];
					int ny = now.y + dy[k];
					
					if(nx<0 || nx>=8 || ny<0 || ny>=8 || map[ny][nx]==1) continue;
					
					if(visited[ny][nx]) continue;
					
					q.offer(new Node(nx, ny));
					visited[ny][nx] = true;
				}
				
			}
			Move_Wall(map);
		}
		
		
		return false;
	}
	
	public static void Move_Wall(int [][] map) {
		for(int i=7;i>0;i--) {
			map[i] = map[i-1].clone();
		}
		for(int i=0;i<8;i++) {
			map[0][i] = 0;
		}
		
	}

	public static class Node{
		int x;
		int y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}