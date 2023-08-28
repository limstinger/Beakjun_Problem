import java.util.*;
import java.io.*;

public class Main {
    static int N, M, Hx, Hy, Ex, Ey, ans = Integer.MAX_VALUE;
    static int map[][];
    static boolean[][][] visited;
    static PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
 
        @Override
        public int compare(Node o1, Node o2) {
            // TODO Auto-generated method stub
            return o1.dis-o2.dis;
        }
    });
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        st = new StringTokenizer(br.readLine(), " ");
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        map = new int[N][M];
        visited = new boolean[2][N][M];
 
        st = new StringTokenizer(br.readLine(), " ");
        Hx = Integer.parseInt(st.nextToken())-1;
        Hy = Integer.parseInt(st.nextToken())-1;
 
        queue.offer(new Node ( Hx, Hy, 0, 0 ));
 
        st = new StringTokenizer(br.readLine(), " ");
 
        Ex = Integer.parseInt(st.nextToken());
        Ey = Integer.parseInt(st.nextToken());
 
        
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        bfs();
        System.out.println(ans==Integer.MAX_VALUE? -1 : ans);
 
    }
 
    public static void bfs() {
        visited[0][Hx][Hy] = true;
        
        while (!queue.isEmpty()) {
        	Node now = queue.poll();
            
            if (now.x == Ex-1 && now.y == Ey-1) {
                ans = now.dis;
                return;
            }
 
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
 
                if (nx >= 0 && ny >= 0 && nx <N && ny <M) {
 
                    if (map[nx][ny] == 0 && !visited[now.cnt][nx][ny]) { 
                        visited[now.cnt][nx][ny] = true;
                        queue.offer(new Node( nx, ny, now.cnt, now.dis+1 ));
                        
                    } else if (map[nx][ny] == 1 && now.cnt==0) {
                        visited[1][nx][ny] = true;
                        queue.offer(new Node ( nx, ny, 1, now.dis+1));
                    }
                }
            }
 
        }
    }
    public static class Node{
        int x, y, cnt, dis;
 
        public Node(int x, int y, int cnt, int dis) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;	// 마법 사용 유무
            this.dis = dis;
        }
        
    }
 
}