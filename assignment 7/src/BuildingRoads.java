// Dedcription:

// Byteland has n cities, and m roads between them. The goal is to construct new roads so that there is a route between any two cities.
// Your task is to find out the minimum number of roads required, and also determine which roads should be built.
// Input:
    
// The first input line has two integers n and m: the number of cities and roads. The cities are numbered 1,2,\dots,n.
// After that, there are m lines describing the roads. Each line has two integers a and b: there is a road between those cities.
// A road always connects two different cities, and there is at most one road between any two cities.
// Output:
    
// First print an integer k: the number of required roads.
// Then, print k lines that describe the new roads. You can print any valid solution.
// Constraints

// 1 <= n <= 10^5
// 1 <= m <= 2 * 10^5
// 1 <= a,b <= n

// Example
// Input:
// 4 2
// 1 2
// 3 4

// Output:
// 1
// 2 3

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
