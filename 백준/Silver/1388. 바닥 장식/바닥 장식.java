import java.util.*;
import java.io.*;

public class Main {
	public static int N, M;
	public static char now;
	public static char [][] map;
	public static boolean [][] visited;
	public static int [] dx = {1, 0};
	public static int [] dy = {0, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char [N][M];
		visited = new boolean [N][M];
		
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		int count = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!visited[i][j]) {
					now = map[i][j];
					DFS(j, i, map[i][j]);
					count++;
				}
			}
		}
		
		System.out.println(count);
		
	}
	
	public static void DFS(int x, int y, char c) {	
		if(c=='-' && now==map[y][x]) {
			visited[y][x] = true;
			if(x<M-1)
				DFS(x+1, y, map[y][x+1]);
		}
		else if(c=='|' && now==map[y][x]) {
			visited[y][x] = true;
			if(y<N-1)
				DFS(x, y+1, map[y+1][x]);
		}
	}

}