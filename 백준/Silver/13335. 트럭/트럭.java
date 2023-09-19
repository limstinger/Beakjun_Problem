import java.util.*;
import java.io.*;

public class Main{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> q = new LinkedList<>();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 트럭 수
		int W = Integer.parseInt(st.nextToken());	// 다리 길이
		int L = Integer.parseInt(st.nextToken());	// 다리의 하중
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int m = Integer.parseInt(st.nextToken());
			q.offer(m);
		}
		
		Queue<Integer> Bridge = new LinkedList<>();
		for(int i=0;i<W;i++) {
			Bridge.offer(0);
		}
		
		int weight = 0;	// 트럭 무게 합
		int time = 0;
		while(!Bridge.isEmpty()) {
			time++;
			weight-=Bridge.poll();
			
			if(!q.isEmpty()) {
				if(q.peek()+weight<=L) {
					int m =q.poll();
					weight+=m;
					Bridge.offer(m);
				}
				else {
					Bridge.offer(0);
				}
			}
		}
		
		System.out.println(time);
	}

}