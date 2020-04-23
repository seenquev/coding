import java.util.Arrays;

public class Grid {
    private boolean mark(int x, int y, char[][] grid) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length) {
            return false;
        }
        if (grid[x][y] == '1') {
            grid[x][y] = '*';
            return true;
        }
        return false;
    }

    private void markNeighbour(int i, int j, char[][] grid) {
        int[] vals = {-1,1};
        for (int l = 0; l <= 1; l++) {
            for (int k = 0; k < vals.length; k++) {
                int x = i + (l == 0 ? vals[k] : 0);
                int y = j + (l == 1 ? vals[k] : 0);

                if (mark(x, y, grid)) {
                    markNeighbour(x, y, grid);
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        int currentId = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char val = grid[i][j];
                if (val == '1') {
                    grid[i][j] = '*';
                    currentId++;
                    markNeighbour(i, j, grid);
                }
            }
        }

        return currentId;
    }

    public void run() {
        char[][] grid = { {'1','1','1'}, {'0', '1', '0'}, {'1', '1', '1'}};
        char[][] grid2 = { {'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        int ret = numIslands(grid2);
        System.out.println(ret);
    }
}
