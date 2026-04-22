import java.io.*;
import java.util.*;

public class BuildingRoads {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static List<Integer> representatives;

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

        visited = new boolean[n + 1];
        representatives = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                representatives.add(i);
                dfs(i);
            }
        }

        int k = representatives.size() - 1;
        System.out.println(k);

        for (int i = 0; i < k; i++) {
            System.out.println(representatives.get(i) + " " + representatives.get(i + 1));
        }
    }

    static void dfs(int node) {
        visited[node] = true;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}