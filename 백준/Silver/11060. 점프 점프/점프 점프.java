import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int [] jump_map;
	public static int [] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int [N];	// 이동 가능 횟수 저장
		jump_map = new int [N];	// 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int jump = Integer.parseInt(st.nextToken());
			map[i] = jump;
			jump_map[i] = Integer.MAX_VALUE;
		}

		BFS();
	}

	public static void BFS() {
		Queue<Node> q = new LinkedList<>();
		jump_map[0] = 0;
		
		q.offer(new Node(0, 0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x;
			int count = now.count;
			
			if(map[x] >=1) {
				for(int i=1;i<=map[x];i++) {
					if(x+i<N && jump_map[x+i] > count+1) {
						jump_map[x+i] = count+1;
						q.offer(new Node(x+i, count+1));
					}
				}
			}
		}
		
		System.out.println(jump_map[N-1]==Integer.MAX_VALUE ? -1:jump_map[N-1]);
	}
	
	public static class Node{
		int x;
		int count;
		Node(int x, int count){
			this.x = x;
			this.count = count;
		}
	}
}
