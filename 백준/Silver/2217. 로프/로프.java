import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Integer [] map = new Integer [N];
		
		for(int i=0;i<N;i++) {
			int rope = Integer.parseInt(br.readLine());	
			map[i] = rope;
		}
		
		Arrays.sort(map, Collections.reverseOrder());
		
		int max_weight = Integer.MIN_VALUE;
		int rope = Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			rope = Math.min(rope, map[i]);
			max_weight = Math.max(max_weight, rope*(i+1));
		}
		
		System.out.println(max_weight);
	}

}
