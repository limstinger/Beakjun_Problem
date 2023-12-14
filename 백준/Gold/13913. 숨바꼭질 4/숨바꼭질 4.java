import java.util.*;
import java.io.*;

public class Main {
	public static int [] t = new int [100001];
	public static int [] parent = new int [100001];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		BFS(N, K);
		
		int index = K;
		Stack<Integer> stack = new Stack<>();
		stack.push(K);
		
		while(index!=N) {
			stack.push(parent[index]);
			index = parent[index];
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(t[K]-1+"\n");
		while(!stack.isEmpty()) {
			sb.append(stack.pop()+" ");
		}

		System.out.println(sb);
	}
	
	public static void BFS(int N, int K) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		t[N] = 1;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(now==K) {
				return;
			}
			
			for(int i=0;i<3;i++) {
				int next = 0;
				if(i==0) 		next = now + 1;
				else if(i==1) 	next = now - 1;
				else if(i==2) 	next = now * 2;
				
				if(next<0 || next>100000) continue;
				
				if(t[next]==0) {
					q.offer(next);
					parent[next] = now;
					t[next] = t[now] + 1;
				}
			}
		}
	}

}