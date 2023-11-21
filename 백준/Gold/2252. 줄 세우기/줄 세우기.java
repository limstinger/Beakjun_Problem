import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 학생 수
		int M = Integer.parseInt(st.nextToken());	// 관계 수
		
		ArrayList<Integer>[] Order = new ArrayList[N+1];
		int [] indegree = new int [N+1];
		
		for(int i=1;i<=N;i++) {
			Order[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int person = Integer.parseInt(st.nextToken());
			int next_person = Integer.parseInt(st.nextToken());
			
			Order[person].add(next_person);
			indegree[next_person]++;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=1;i<=N;i++) {
			if(indegree[i]==0) {
				pq.add(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			int person = pq.poll();
			
			sb.append(person+" ");
			
			for(int p : Order[person]) {
				indegree[p]--;
				if(indegree[p]==0) {
					pq.add(p);
				}
			}
		}
		
		System.out.println(sb);
	}

}
