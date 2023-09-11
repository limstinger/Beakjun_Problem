import java.util.*;
import java.io.*;

public class Main {
	public static int N, M;
	public static int [][] link;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		link = new int [M][2];
		
		for(int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			link[i][0] = a;
			link[i][1] = b;
		}
		
		BFS();
	
	}
	
	public static void BFS() {
		Queue<Node> q = new LinkedList<>();
		boolean [] visited = new boolean [N+1];
		int count = 0;
		
		q.offer(new Node(1, 0));
		
		while(!q.isEmpty()) {
			Node friend = q.poll();
			
			for(int i=0;i<M;i++) {
				if(link[i][0] == friend.num && !visited[link[i][1]] && friend.check!=2) {
					q.offer(new Node(link[i][1], friend.check+1));
					visited[link[i][1]] = true;
				}
				
				else if(link[i][1] == friend.num && !visited[link[i][0]] && friend.check!=2) {
					q.offer(new Node(link[i][0], friend.check+1));
					visited[link[i][0]] = true;
				}
			}
		}
		
		for(int i=2;i<=N;i++) {
			if(visited[i]) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	public static class Node{
		int num;
		int check;
		Node(int num, int check){
			this.num = num;
			this.check = check;
		}
	}
}