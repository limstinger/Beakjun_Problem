import java.util.*;
import java.io.*;

public class Main {
	public static int N,M;
	public static int [] indegree;
	public static ArrayList<Road>[] list;
	public static int [] order;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	// 도로의 번호
		M = Integer.parseInt(br.readLine());	// 도로의 개수
		
		indegree = new int [N+1];	// 위상 정렬 용도
		order = new int [N+1];	// 지나간 지역 순서 저장
		list = new ArrayList[N+1];
		
		for(int i=1;i<=N;i++) {
			list[i] =new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());	// 출발지점
			int q = Integer.parseInt(st.nextToken());	// 도착지점
			int r = Integer.parseInt(st.nextToken());	// p->q 로 갈 때 얻는 점수
			
			list[p].add(new Road(q, r));	
			indegree[q]++;	
		}

		topological_sort(1);
	}
	
	public static void print(int p, int s) {
		if(p == s) {
			System.out.print(p+" ");
			return;
		}
		print(order[p], s);
		System.out.print(p+" ");
	}
	
	public static void topological_sort(int s) {
		Queue<Road> q = new LinkedList<>();
		
		int [] dp = new int [N+1];		// 점수 저장
		
		q.offer(new Road(s, 0));	// 출발지점과 현재 점수
		
		while(!q.isEmpty()) {
			Road current = q.poll();
			
			if(current.q==s && indegree[s]==0) {
				break;
			}
			
			for(int i=0;i<list[current.q].size();i++) {
				Road next = list[current.q].get(i);
				
				if(dp[current.q]+next.r > dp[next.q]) {		// 현재 부여된 점수의 합+다음에 지나갈 도로에 부여된 점수
					dp[next.q] = dp[current.q]+next.r;		
					order[next.q] = current.q;		// 순서 저장
				}
				
				indegree[next.q]--;
				if(indegree[next.q] == 0) {
					q.offer(new Road(next.q, next.r+current.r));
				}
				
			}	
		}
		
		System.out.println(dp[s]);
		print(order[s], s);
		System.out.print(s);
	}

}

class Road{
	int q;	// 도착지점
	int r;	// 점수
	Road(int q, int r){
		this.q = q;
		this.r = r;
	}
}
