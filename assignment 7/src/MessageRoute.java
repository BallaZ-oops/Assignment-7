// Description:

// Syrjälä's network has n computers and m connections. 
// Your task is to find out if Uolevi can send a message to Maija, and if it is possible, what is the minimum number of computers on such a route.
    
// Input:
// The first input line has two integers n and m: the number of computers and connections. The computers are numbered 1,2,\dots,n. Uolevi's computer is 1 and Maija's computer is n.
// Then, there are m lines describing the connections. Each line has two integers a and b: there is a connection between those computers.
// Every connection is between two different computers, and there is at most one connection between any two computers.

// Output:
// If it is possible to send a message, first print k: the minimum number of computers on a valid route. After this, print an example of such a route. You can print any valid solution.
// If there are no routes, print "IMPOSSIBLE".
// Constraints

// 2 <= n <= 10^5
// 1 <= m <= 2 * 10^5
// 1 <= a,b <= n

// Example
// Input:
// 5 5
// 1 2
// 1 3
// 1 4
// 2 3
// 5 4

// Output:
// 3
// 1 4 5

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
