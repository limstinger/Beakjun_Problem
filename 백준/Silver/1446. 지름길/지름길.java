import java.util.*;
import java.io.*;

public class Main {
	public static int N,D;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 지름길 갯수
		D = Integer.parseInt(st.nextToken());	// 고속도로 길이

		List<Path> list = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());	// 출발점
			int e = Integer.parseInt(st.nextToken());	// 도착점
			int l = Integer.parseInt(st.nextToken());	// 길이
			
			if(e>D) {	// 역주행 불가
				continue;
			}
			if(e-s<=l) {	// 지름길이 아님
				continue;
			}
			list.add(new Path(s,e,l));
		}
		
		// 오름차순 정렬
		Collections.sort(list, new Comparator<Path>() {
			@Override
			public int compare(Path o1 , Path o2) {
				if(o1.s == o2.s) {
					return o1.e - o2.e;
				}
				return o1.s - o2.s;
			}
		});
		
		int idx = 0, move=0;	// 현 위치
		int [] dist = new int [10001];
		Arrays.fill(dist, 10001);
		dist[0] = 0;
		
		// 다익스트라
		while(move < D) {
			if(idx < list.size()) {
				Path p = list.get(idx);
				if(move == p.s) {
					dist[p.e] = Math.min(dist[move] + p.l, dist[p.e]);
					idx++;
				}
				else {
					dist[move+1] = Math.min(dist[move+1], dist[move]+1);
					move++;
				}
			}
			else {
				dist[move+1] = Math.min(dist[move+1], dist[move]+1);
				move++;
			}
		}
		System.out.println(dist[D]);
	}
	
	public static class Path{
		int s;
		int e;
		int l;
		Path(int s, int e, int l){
			this.s = s;
			this.e = e;
			this.l = l;
		}
	}
}