import java.util.*;
import java.io.*;

public class Main {
	public static int N,M;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] graph = new ArrayList[N+1];
		int [] indegree = new int [N+1];
		
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		PriorityQueue<Integer> problem = new PriorityQueue<>();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int next_num = Integer.parseInt(st.nextToken());
			
			graph[num].add(next_num);
			indegree[next_num]++;
		}
		
		for(int i=1;i<=N;i++) {
			if(indegree[i]==0) {
				problem.add(i);
			}
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		
		while(!problem.isEmpty()) {
			int current_problem = problem.poll();
			result.add(current_problem);
			
			for(int next : graph[current_problem]) {
				indegree[next]--;
				if(indegree[next]==0) {
					problem.add(next);
				}
			}
		}
		
		for(int num : result) {
			System.out.print(num+" ");
		}
 	}
}
