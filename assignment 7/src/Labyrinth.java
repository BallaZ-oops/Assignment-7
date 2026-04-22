// Description:

// You are given a map of a labyrinth, and your task is to find a path from start to end. You can walk left, right, up and down.
    
// Input:
// The first input line has two integers n and m: the height and width of the map.
// Then there are n lines of m characters describing the labyrinth. Each character is . (floor), # (wall), A (start), or B (end). There is exactly one A and one B in the input.
    
// Output:
// First print "YES", if there is a path, and "NO" otherwise.
// If there is a path, print the length of the shortest such path and its description as a string consisting of characters L (left), R (right), U (up), and D (down). You can print any valid solution.
// Constraints

// 1 <= n,m <= 1000

// Example
// Input:
// 5 8
// ########
// #.A#...#
// #.##.#B#
// #......#
// ########

// Output:
// YES
// 9
// LDDRRRRRU

import java.io.*;
import java.util.*;

public class Labyrinth {
    static int n, m;
    static char[][] grid;
    static int[][] dist;
    static char[][] prevDir;
    static int[] dx = { -1, 1, 0, 0 }; // U, D, L, R
    static int[] dy = { 0, 0, -1, 1 };
    static char[] dirChar = { 'U', 'D', 'L', 'R' };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new char[n][m];
        dist = new int[n][m];
        prevDir = new char[n][m];

        int startX = -1, startY = -1;
        int endX = -1, endY = -1;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            grid[i] = line.toCharArray();
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'A') {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 'B') {
                    endX = i;
                    endY = j;
                }
                dist[i][j] = -1;
            }
        }

        // BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        dist[startX][startY] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] != '#' && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    prevDir[nx][ny] = dirChar[d];
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        if (dist[endX][endY] == -1) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(dist[endX][endY]);

            StringBuilder path = new StringBuilder();
            int x = endX;
            int y = endY;

            while (x != startX || y != startY) {
                char d = prevDir[x][y];
                path.append(d);
                if (d == 'U') x++;
                else if (d == 'D') x--;
                else if (d == 'L') y++;
                else if (d == 'R') y--;
            }

            path.reverse();
            System.out.println(path.toString());
        }
    }
}
