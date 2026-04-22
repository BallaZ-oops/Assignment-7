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