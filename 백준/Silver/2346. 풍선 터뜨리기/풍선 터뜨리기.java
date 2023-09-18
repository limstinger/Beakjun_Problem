import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 ArrayDeque<balloon> list = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1;i<=N;i++) {
			int move = Integer.parseInt(st.nextToken());
			list.offer(new balloon(i, move));
		}
		
		while(list.size() > 1) {
			balloon b = list.pollFirst();
			sb.append(b.number).append(" ");
			
			int move = b.move;
			if(move > 0) {
				for(int j=1;j<move;j++) {
					list.offerLast(list.pollFirst());
				}
			}
			else if(move < 0) {
				for(int j=move;j!=0;j++) {
					list.offerFirst(list.pollLast());
				}
			}
			
		}
		sb.append(list.poll().number);
		System.out.print(sb);
	}
	
	public static class balloon{
		int number;
		int move;
		balloon(int number, int move){
			this.number = number;
			this.move = move;
		}	
	}
}