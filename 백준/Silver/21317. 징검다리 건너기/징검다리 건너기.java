import java.util.*;
import java.io.*;

public class Main {
	static int N, K, min=Integer.MAX_VALUE;
	static int[][] Rock;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		if(N==1) {
			System.out.println(0);
			return;
		}
		Rock = new int[N][2];
		for(int i=1;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Rock[i][0]=Integer.parseInt(st.nextToken());	// 작은 점프
			Rock[i][1]=Integer.parseInt(st.nextToken());	// 큰 점프
		}
		K = Integer.parseInt(br.readLine());
		
		DFS(1, false, 0);
		System.out.println(min);
	}
	private static void DFS(int cur, boolean use, int value) {
		//현재 위치 저장 K를 사용했는지 여부 저장 사용 값 저장 
		if(cur == N) {
			min = Math.min(min, value);
			return;
		}
		if(value > min) {
			return;
		}
		
		// 한칸 건너기
		if(cur + 1 <= N) {
			DFS(cur +1, use, value + Rock[cur][0]);
		}
		if(cur + 2 <= N) {
			DFS(cur +2, use, value + Rock[cur][1]);
		}
		if(!use && cur + 3 <= N) {
			DFS(cur +3, true, value + K);
		}
		
	}

}
