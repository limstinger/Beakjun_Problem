import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=N;i++) {
        	q.offer(i);
        }
        
        sb = new StringBuilder();
        sb.append("<");
        while(!q.isEmpty()) {
        	for(int i=0;i<K-1;i++) {
        		int num = q.poll();
        		q.offer(num);
        	}
        	int num = q.poll();
        	if(q.size()>=1) {
        		sb.append(num+", ");
        	}
        	else
        		sb.append(num);
        }
        sb.append(">");
        System.out.println(sb);
    }
}