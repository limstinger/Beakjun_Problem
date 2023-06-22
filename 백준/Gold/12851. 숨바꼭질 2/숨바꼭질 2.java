import java.util.*;
import java.awt.Point;
import java.io.*;

// BFS 알고리즘
public class Main {
	public static int N,x, count;
	public static int minTime = Integer.MAX_VALUE;
	public static int [] time;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());	// 수빈이 위치
		N = Integer.parseInt(st.nextToken());	// 동생 위치
		
		time = new int [100001];
		
		search();
		
		System.out.println(minTime+"\n"+count);
	}

	public static void search() {
		Queue<Point> q = new LinkedList<>();
		count = 0;
		time[x] = 1;
		
		q.offer(new Point(x,0));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(minTime < time[now.y]) return;
			
			if(now.x == N) {
				if(count==0) {	// 처음 방문했을 시
					minTime=now.y;
				}
				if(minTime==now.y) {
					count++;
				}
				continue;
			}
			
			int [] dx = {now.x-1, now.x+1, now.x*2};
			for(int i=0;i<3;i++) {
				int next = dx[i];
				if(next<0 || next > 100000) continue;
				if(time[next]==0 || time[next]==now.y+1) {
					time[next] = now.y+1;
					q.offer(new Point(next, now.y+1));
				}
				
			}
		}
	}
}
