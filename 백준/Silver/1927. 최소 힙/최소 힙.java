import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        
       int N = Integer.parseInt(br.readLine());
       
       PriorityQueue<Integer> pq = new PriorityQueue<>();
       sb = new StringBuilder();
       for(int i=0;i<N;i++) {
    	   int num = Integer.parseInt(br.readLine());
    	   
    	   if(num!=0) {
    		   pq.offer(num);
    	   }
    	   else {
    		   if(pq.size()==0) {
    			   sb.append(0+"\n");
    		   }
    		   else {
    			   sb.append(pq.poll()+"\n");
    		   }
    	   }
       }
       
       System.out.println(sb);
    }
}