import java.util.*;
import java.io.*;

public class Main {
	public static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		int [] num = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num);
		
		TwoPoint(num);
	}
	
	public static void TwoPoint(int [] num) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(int i=0;i<N;i++) {
			int left = 0;
			int right = N-1;
			while(true) {
				if(left==i) left++;
				else if(right==i) right--;
				
				int sum = num[left] + num[right];
				
				if(left>=right) break;
				
				if(sum==num[i]) {
					count++;
					break;
				}
				else if(sum > num[i]) {
					right--;
				}
				else if(sum < num[i]) {
					left++;
				}
			}
			 
		}
		System.out.println(count);
	}

}
