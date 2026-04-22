// Description

// There are n pupils in Uolevi's class, and m friendships between them. 
// Your task is to divide the pupils into two teams in such a way that no two pupils in a team are friends. 
// You can freely choose the sizes of the teams.
    
// Input:

// The first input line has two integers n and m: the number of pupils and friendships. The pupils are numbered 1,2,\dots,n.
// Then, there are m lines describing the friendships. Each line has two integers a and b: pupils a and b are friends.
// Every friendship is between two different pupils. You can assume that there is at most one friendship between any two pupils.
// Output:
    
// Print an example of how to build the teams. For each pupil, print "1" or "2" depending on to which team the pupil will be assigned. You can print any valid team.
// If there are no solutions, print "IMPOSSIBLE".
// Constraints

// 1 <= n <= 10^5
// 1 <= m <= 2 * 10^5
// 1 <= a,b <= n

// Example
// Input:
// 5 3
// 1 2
// 1 3
// 4 5

// Output:
// 1 2 2 1 2

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
