import java.io.*;
import java.util.*;

public class BuildingTeams {
    static ArrayList<Integer>[] graph;
    static int[] color;
    static boolean possible = true;

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

        color = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                if (!bfs(i)) {
                    possible = false;
                    break;
                }
            }
        }

        if (!possible) {
            System.out.println("IMPOSSIBLE");
        } else {
            for (int i = 1; i <= n; i++) {
                System.out.print(color[i] + " ");
            }
            System.out.println();
        }
    }

    static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 1;
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph[current]) {
                if (color[neighbor] == 0) {
                    color[neighbor] = (color[current] == 1) ? 2 : 1;
                    queue.add(neighbor);
                } else if (color[neighbor] == color[current]) {
                    return false;
                }
            }
        }
        return true;
    }
}