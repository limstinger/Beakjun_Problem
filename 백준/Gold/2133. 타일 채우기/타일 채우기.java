import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int [] dp = new int [Math.max(N/2, 2)];
		dp[0] = 3;
		dp[1] = 11;
		
		int tmp = 0;
		
		if(N%2==0) {
			for(int i=2;i<N/2;i++) {
				dp[i] = dp[i-1] * 3 + 2 + (tmp+=(dp[i-2] * 2));
			}	
			System.out.println(dp[N/2-1]);
		}
		else {
			System.out.println(0);
		}
		
	}

}
