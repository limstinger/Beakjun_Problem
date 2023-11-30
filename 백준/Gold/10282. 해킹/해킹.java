import java.util.*;
import java.io.*;

public class Main {
	public static int n, c;
	public static int INF = 987654321;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());	// 테스트케이스 수
		
		for(int i=0;i<TC;i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());	// 컴퓨터 수
			int d = Integer.parseInt(st.nextToken());	// 의존성 수
			c = Integer.parseInt(st.nextToken());	// 해킹당한 컴퓨터 번호
			
			ArrayList<Computer>[] com = new ArrayList[n+1];
			
			for(int j=1;j<=n;j++) {
				com[j] = new ArrayList<>();
			}
			
			for(int j=0;j<d;j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());	
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				com[b].add(new Computer(a, s));
			}
			
			Algorithm(com);
		}
		
		System.out.println(sb);
		
	}
	
	public static void Algorithm(ArrayList<Computer>[] com) {
		Queue<Computer> q = new LinkedList<>();
		int [] t = new int [n+1];
		Arrays.fill(t, INF);
		int time = 0;
		int count = 0;
		t[c] = 0;
		
		q.offer(new Computer(c, 0));
		
		while(!q.isEmpty()) {
			Computer v = q.poll();
			for(Computer link : com[v.from]) {
				if(t[link.from] > v.s + link.s) {
					t[link.from] = v.s + link.s;
					q.offer(new Computer(link.from, v.s + link.s));
				}
			}
		}
		
		for(int i=1;i<=n;i++) {
			if(t[i]!=INF) {
				count++;
				time = Math.max(time, t[i]);
			}
		}
		
		sb.append(count+" "+time+"\n");
	}

	public static class Computer{
		int from;
		int s;
		Computer(int from, int s){
			this.from = from;
			this.s = s;
		}
	}
}
