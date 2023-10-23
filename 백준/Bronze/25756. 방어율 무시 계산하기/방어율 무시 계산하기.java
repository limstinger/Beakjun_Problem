import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		double n_ERA = 0;	// 현재 방어율
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int doping = Integer.parseInt(st.nextToken());
			
			double ERA = 100-((100-doping)*(100-n_ERA))/100;
			
			System.out.println(ERA);
			
			n_ERA = ERA;
		}
	}
}