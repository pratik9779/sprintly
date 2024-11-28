package com.sprintly.sprintly.config;

import java.util.Arrays;

public class WebSecurityConfig {


    public int minimumObstacles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] visited = new int[n][m];
        boolean[][] v2 = new boolean[n][m];
        Arrays.stream(visited).forEach(row -> Arrays.fill(row, -1));

        int ans = dfs(0, 0, grid, visited, v2);
        return ans;
    }

    int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int dfs(int r, int c, int[][] grid, int visited[][], boolean[][] visted2) {
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return 0;
        }
        if (visited[r][c] != -1) return visited[r][c];
        if (visted2[r][c]) return 9999;
        visted2[r][c] = true;
        int count = 9999;
        for (int[] d : dir) {
            int rr = r + d[0];
            int cc = c + d[1];
            if (rr >= 0 && cc >= 0 && rr < grid.length && cc < grid[0].length) {

                int next = dfs(rr, cc, grid, visited, visted2);
                count = Math.min(count, next);
            }
        }
        visted2[r][c] = false;
        return visited[r][c] = (grid[r][c] == 1 ? count + 1 : count);
    }

}
