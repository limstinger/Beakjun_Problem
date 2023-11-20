import java.util.*;
import java.io.*;

public class Main {
    static int[] arr;
    static int[] check;
    static List<List<Integer>> v;
    static PriorityQueue<Pair> pq;

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Compare implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            if (a.first == b.first)
                return Integer.compare(a.second, b.second);
            return Integer.compare(a.first, b.first);
        }
    }

    static void dfs(int x, List<Integer> buf) {
        if (check[x] == 1) {
            return;
        }
        check[x] = 1;
        for (int i = 0; i < v.get(x).size(); i++) {
            buf.add(v.get(x).get(i));
            dfs(v.get(x).get(i), buf);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        arr = new int[200000];
        check = new int[100002];
        v = new ArrayList<>(n + 1);
        pq = new PriorityQueue<>(new Compare());

        for (int i = 0; i <= n; i++) {
            v.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int input = Integer.parseInt(st.nextToken());
            v.get(i).add(input);
            arr[input] += 1;
        }

        List<Integer> tp = new ArrayList<>();
        dfs(1, tp);

        for (int i = 1; i <= n; i++)
            pq.add(new Pair(arr[i], i));

        while (!pq.isEmpty()) {
            int cval = pq.peek().first;
            int cidx = pq.peek().second;
            pq.poll();

            if (check[cidx] == 1) continue;
            tp.add(cidx);
            dfs(cidx, tp);
        }

        System.out.println(tp.size());
        for (int i = 0; i < tp.size(); i++) {
            System.out.print(tp.get(i) + " ");
        }
    }
}