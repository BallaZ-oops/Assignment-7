import java.io.*;
import java.util.*;

public class MessageRoute {
    static ArrayList<Integer>[] graph;
    static int[] prev;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        prev = new int[n + 1];
        dist = new int[n + 1];
        Arrays.fill(prev, -1);
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        dist[1] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph[current]) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[current] + 1;
                    prev[neighbor] = current;
                    queue.add(neighbor);

                    if (neighbor == n) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        if (dist[n] == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            List<Integer> path = new ArrayList<>();
            int current = n;
            while (current != -1) {
                path.add(current);
                current = prev[current];
            }
            Collections.reverse(path);

            System.out.println(path.size());
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(path.get(i));
            }
            System.out.println();
        }
    }
}