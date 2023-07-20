import java.util.*;
import java.io.*;

public class Main {
	public static int N;
	public static int [][] Rock;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		Rock = new int [N][3];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				int r = Integer.parseInt(st.nextToken());	
				Rock[i][j] = r;
			}		
		}
		
		for(int i=0;i<N;i++) {
			Arrays.sort(Rock[i]);
			game(i);
		}
		
	}
	public static void game(int a) {
		int count = 0;		// 시행된 횟수		
		
		if((Rock[a][1]-Rock[a][0])%2==0) {
			int gap = Rock[a][1]-Rock[a][0];
			
			count += (2*(gap/2) + Rock[a][0]);
		}
		
		else if((Rock[a][2]-Rock[a][0])%2==0) {
			int gap = Rock[a][2]-Rock[a][0];
			
			count += (2*(gap/2) + Rock[a][0]);
		}
		
		else if((Rock[a][2]-Rock[a][1])%2==0) {
			int gap = Rock[a][2]-Rock[a][1];
			
			count += (2*(gap/2) + Rock[a][1]);
		}
		
		if(count%2==0) {
			System.out.println("R");
		}
		else {
			System.out.println("B");
		}
	}

}