import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int [] time = new int [N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		// 배열 정렬
		Arrays.sort(time);
		
		System.out.println(ATM(time));
	}
	
	public static int ATM(int [] time) {
		int result = 0;
		int [] person = new int [N];
		
		person[0] = time[0]; 	// 첫번째 사람
		
		for(int i=1;i<N;i++) {
			person[i] = time[i] + person[i-1];
		}
		
		for(int i=0;i<N;i++) {
			result += person[i];
		}
		
		return result;
	}

}
