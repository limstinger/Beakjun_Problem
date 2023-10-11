import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 마법의 두루마리 입력
		String s = br.readLine();
		String [] bridge = {br.readLine(), br.readLine()};
		int ans = 0;
		
		for(int select=0;select<=1;select++) {	// 다리 선택 
			int [][] dp = new int [s.length()+1][bridge[0].length()+1];
			Arrays.fill(dp[0], 1);
			for(int i=1;i<=s.length();i++) { // 마법의 두루마리 길이만큼
				String m = bridge[(i-select) & 1];	// i-select번째 문자(bridge[0]과 bridge[1]를 번가락 가르킨다)
				for(int j=1;j<=m.length();j++) {
					dp[i][j] = 	dp[i][j-1] + (m.charAt(j-1)==s.charAt(i-1) ? dp[i-1][j-1] : 0);
				}
			}
			ans+=dp[s.length()][bridge[0].length()];
		}
		
		System.out.println(ans);
	}
}