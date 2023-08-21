import java.util.*;
import java.io.*;

public class Main {
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static int N, M;
    static int count;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(b).add(a);
        }
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            count = 0;
            bfs(i);
            result[i] = count;
            max = Math.max(count, max);

        }
        for (int i = 1; i <= N; i++) {
            if (result[i] == max)
                sb.append(i+" ");
        }
        System.out.println(sb);

    }

    public static void bfs(int from) {
        Queue<Integer> q = new LinkedList<>();
        q.add(from);
        visited[from] = true;
        while (!q.isEmpty()) {
            int Heck = q.poll();
            for(int i : list.get(Heck)){
                if(!visited[i]){
                    q.add(i);
                    visited[i] = true;
                    count++;
                }
            }

        }
    }
}