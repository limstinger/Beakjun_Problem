import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Top> top = new Stack<>();
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if(top.isEmpty()) {	// 스택이 비어있으면
				sb.append("0 ");
				top.push(new Top(i, height));
			}
			
			else {
				while(true) {
					if(top.isEmpty()) {
						sb.append("0 ");
						top.push(new Top(i, height));
						break;
					}
					
					Top t = top.peek();
					
					if(t.height > height) {	// peek한 탑의 높이가 더 높을 시 
						sb.append(t.num+" ");
						top.push(new Top(i, height));
						break;
					}
					else { // peek한 탑의 높이보다 입력한 탑의 높이가 더 높을 시
						top.pop();
					}
				}
			}
		}
		
		System.out.println(sb);
	}

	
	public static class Top{
		int num;
		int height;
		Top(int num, int height){
			this.num = num;
			this.height = height;
		}
	}
}