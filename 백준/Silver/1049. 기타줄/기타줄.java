import java.util.*;
import java.io.*;

public class Main{
	public static int [] P_price;		// 패키지 가격 저장
	public static int [] S_price;
	public static int N, M;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//	끊어진 기타줄
		M = Integer.parseInt(st.nextToken());	//	기타줄 브랜드
		
		P_price = new int [M];
		S_price = new int [M];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
				int p_price = Integer.parseInt(st.nextToken());		// 패키지 가격(줄 6개)
				int s_price = Integer.parseInt(st.nextToken());		// 낱개 가격(줄 1개)
				
				P_price[i] = p_price;
				S_price[i] = s_price;
		}
		
		Arrays.sort(P_price);
		Arrays.sort(S_price);
		
		/*Arrays.sort(price, new Comparator<int[]>() {
			@Override
			public int compare(int [] o1, int[] o2) {
				if(o1[0] != o2[0]) {
					return o2[0] - o1[0];
				}
				return o1[1] - o2[1];
			}
		});*/
		
		System.out.println(Greedy());
	}
	
	public static int Greedy() {
		int result = Integer.MAX_VALUE;
		
		// 낱개로만 vs 패키지로만 vs 낱개+패키지
		int tmp = N/6;
		result = Math.min((tmp)*P_price[0]+(N-tmp*6)*S_price[0], (tmp+1)*P_price[0]);		// 낱개+패키지 vs 패키지
		result = Math.min(result, N*S_price[0]);
			
		return result;
	}

}
