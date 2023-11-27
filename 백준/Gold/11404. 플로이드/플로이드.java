import java.util.*;
import java.io.*;

public class Main {
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());	// 도시의 개수
		int M = Integer.parseInt(br.readLine());	// 길의 개수
		
		int [][] Road = new int [N+1][N+1];			// 길 정보 저장
		
		for(int i=1;i<=N;i++) {
			Arrays.fill(Road[i], INF);
			Road[i][i] = 0;
		}
		
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int to = Integer.parseInt(st.nextToken());		// 시작 도시
			int from = Integer.parseInt(st.nextToken());	// 도착 도시
			int c = Integer.parseInt(st.nextToken());		// 비용
			
			Road[to][from] = Math.min(Road[to][from], c);
		}
		
		// 플로이드 마샬 알고리즘
		for(int k=1;k<=N;k++) {	// 경유지
			for(int i=1;i<=N;i++) {	// 출발지
				if(k==i) continue;
				for(int j=1;j<=N;j++) {	// 도착지
					if(i==j || j==k) continue;
					if(Road[i][j] > Road[i][k] + Road[k][j]) {
						Road[i][j] = Road[i][k] + Road[k][j];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(Road[i][j]==INF) {
					sb.append(0+" ");
				}
				else
					sb.append(Road[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
