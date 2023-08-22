import java.util.*;
import java.io.*;

public class Main {
    static int N, K, S, X, Y;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 실험관 크기
        K = Integer.parseInt(st.nextToken()); // 바이러스 번호 (1 ~ K)

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    visited[i][j] = true; // 이미 바이러스가 있는 곳 방문 체크
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()); // 시간
        X = Integer.parseInt(st.nextToken()); // X 위치
        Y = Integer.parseInt(st.nextToken()); // Y 위치

        BFS();
        
        System.out.println(map[X][Y]);
    }

    public static void BFS() {
        Queue<Node> q = new LinkedList<>();
        
        // 초기 바이러스 정보 큐에 넣기
        for (int k = 1; k <= K; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] == k) {
                        q.offer(new Node(j, i, k));
                    }
                }
            }
        }
        
        int time = 0;
        
        while (!q.isEmpty() && time < S) {
            int size = q.size();
            time++;
            
            for (int i = 0; i < size; i++) {
                Node virus = q.poll();
                int x = virus.x;
                int y = virus.y;
                int num = virus.num;

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx >= 1 && ny >= 1 && nx <= N && ny <= N && map[ny][nx] == 0 && !visited[ny][nx]) {
                        map[ny][nx] = num;
                        visited[ny][nx] = true;
                        q.offer(new Node(nx, ny, num));
                    }
                }
            }
        }
    }

    public static class Node {
        int x;
        int y;
        int num;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
