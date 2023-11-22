import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		int T = Integer.parseInt(br.readLine());	// 테스트케이스 수
		
		sb = new StringBuilder();
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 건물의 개수 
			int K = Integer.parseInt(st.nextToken());	// 건물순서 규칙의 개수
			
			int [] time = new int [N+1];
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				time[j] = Integer.parseInt(st.nextToken());
			}
			
			ArrayList<Integer>[] Order = new ArrayList[N+1];
			ArrayList<Integer>[] t = new ArrayList[N+1];	// build에 저장된 건물들을 지은 후 다음 건물을 지을 때  
			int [] indegree = new int [N+1];
			
			for(int j=1;j<=N;j++) {
				Order[j] = new ArrayList<>();
				t[j] = new ArrayList<>();
			}
			
			for(int j=0;j<K;j++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				Order[X].add(Y);
				indegree[Y]++;
			}
			
			int target = Integer.parseInt(br.readLine());	// 지어야 하는 건물 번호
			
			PriorityQueue<Building> build = new PriorityQueue<>();
			
			for(int j=1;j<=N;j++) {
				if(indegree[j]==0) {
					build.add(new Building(j, 0));
				}
			}
			
			int max_t = 0;
			while(!build.isEmpty()) {
				Building building = build.poll();
				
				int b_t = building.time + time[building.num];
				
				if(building.num==target) {
					max_t = Math.max(b_t, max_t);
					sb.append(max_t+"\n");
					break;
				}
				
				for(int num : Order[building.num]) {
					indegree[num]--;
					t[num].add(b_t);
					if(indegree[num]==0) {
						Collections.sort(t[num], Collections.reverseOrder());
						int get_t = t[num].get(0);
						build.add(new Building(num, get_t));
					}
				}
			}
		}
		
		System.out.println(sb);
	}
	
	public static class Building implements Comparable<Building>{
		int num;
		int time;
		Building(int num, int time){
			this.num = num;
			this.time = time;
		}
		
		@Override
		public int compareTo(Building b) {
			if(this.time==b.time) {
				return this.num - b.num;
			}
			return this.time - b.time;
		}
	}
}