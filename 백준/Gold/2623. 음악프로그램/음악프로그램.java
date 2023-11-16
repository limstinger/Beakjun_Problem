import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 가수의 수
		int M = Integer.parseInt(st.nextToken());	// 보조 PD 수
		
		ArrayList<Integer>[] Graph = new ArrayList[N+1];
		int [] indegree = new int [N+1];
		
		for(int i=1;i<=N;i++) {
			Graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			int singer = 0;
			int next_singer = 0;
			for(int j=0;j<num-1;j++) {	
				if(j==0) {
					singer = Integer.parseInt(st.nextToken());
				}
				next_singer = Integer.parseInt(st.nextToken());
				
				Graph[singer].add(next_singer);
				indegree[next_singer]++;
				singer = next_singer;
			}
		}
		
		PriorityQueue<Integer> Order = new PriorityQueue<>();
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i=1;i<=N;i++) {
			if(indegree[i]==0) {
				Order.add(i);
			}
		}
		
		while(!Order.isEmpty()) {
			int singer = Order.poll();
			result.add(singer);
			
			for(int next : Graph[singer]) {
				indegree[next]--;
				if(indegree[next]==0) {
					Order.add(next);
				}
			}
		}

		if(result.size()==N) {
			for(int singer : result) {
				System.out.println(singer);
			}
		}
		else {
			System.out.println("0");
		}	
	}
}