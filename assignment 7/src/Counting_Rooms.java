//Description:

// You are given a map of a building, and your task is to count the number of its rooms. The size of the map is n 
//times m squares, and each square is either floor or wall. You can walk left, right, up, and down through the floor squares.

// Input:
// The first input line has two integers n and m: the height and width of the map.
// Then there are n lines of m characters describing the map. Each character is either . (floor) or # (wall).

// Output:
// Print one integer: the number of rooms.
// Constraints

// 1 <= n,m <= 1000

// Example
// Input:
// 5 8
// ########
// #..#...#
// ####.#.#
// #..#...#
// ########

// Output:
// 3

import java.io.*;
import java.util.*;

public class Counting_Rooms {
    static int n, m;
    static char[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            grid[i] = line.toCharArray();
        }

        int rooms = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.' && !visited[i][j]) {
                    rooms++;
                    dfs(i, j);
                }
            }
        }

        System.out.println(rooms);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == '.' && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }
}
