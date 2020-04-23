import java.util.*;

public class AmazonQ2 {

    private static void printGrid(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
                if (j != array[0].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print('\n');
        }
    }

    private static void breadthSearchFirst (List<List<Integer>> grid) {
        int rows = grid.size();
        int cols = grid.get(0).size();

        // Create visited
        int array[][] = new int [rows][cols];

        // Initiate list of servers that have files
        Queue<Queue<Position>> queue = new LinkedList<>();
        Queue<Position> innerQ = new LinkedList<Position>();
        queue.add(innerQ);

        int y = 0;
        int x = 0;
        for (List<Integer> row : grid) {
            for (int i = 0; i < row.size(); i++) {
                int value = row.get(i);
                array[y][x] = 0;

                if (value == 1) {
                    innerQ.add(new Position(x, y));
                }
                x++;
            }
            y++;
            x = 0;
        }

        printGrid(array);

        int totalCount = 0;
        int roundCount = 0;

        // Breadth deep search
        while (!queue.isEmpty()) {

            Queue<Position> innerQ_ = queue.poll();
            Queue<Position> newQ = new LinkedList<>();
            while (!innerQ_.isEmpty()) {

                Position pos = innerQ_.poll();
                if (array[pos.y][pos.x] == 1) {
                    continue;
                }
                // Mark as visited
                array[pos.y][pos.x] = 1;

                // Print current state
                System.out.println("-----");
                System.out.println("Exam: pos.x: " + pos.x + ", pos.y: " + pos.y);
                printGrid(array);

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((i == 0 && j == 0) || (i != 0 && j != 0)) {
                            continue;
                        }
                        int realx = pos.x + i;
                        int realy = pos.y + j;

                        if (realx < 0 || realx >= cols) {
                            continue;
                        }
                        if (realy < 0 || realy >= rows) {
                            continue;
                        }
                        if (array[realy][realx] == 0) {
                            newQ.add(new Position(realx, realy));
                            System.out.println("Added: pos.x: " + realx + ", pos.y: " + realy);
                        }
                    }
                }
                totalCount++;
            }
            if (!newQ.isEmpty()) {
                queue.add(newQ);
            }
            roundCount++;
        }
        System.out.println("Total count: " + totalCount + ", minimum hours: " + roundCount);
    }

    public void run() {
        System.out.println("Hello World!");

        List<List<Integer>> grid = new ArrayList<>();
        grid.add(new ArrayList<Integer>(Arrays.asList(0, 1, 0, 0, 0, 0, 0, 0, 0, 0)));
        grid.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
        grid.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 0, 0, 0, 0, 0)));
        grid.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 0)));
        grid.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0, 0, 0)));

        breadthSearchFirst(grid);
    }

    private static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    };
}
