import java.util.*;

public class Task {
    private void findCriticalConnections(List<List<Integer>> connections, List<List<Integer>> criticals) {
        if (connections.isEmpty()) {
            return;
        }

        TreeMap<Integer, List<Integer>> connectionsMap = new TreeMap<>();

        // Create a map of adjacents
        {
            for (List<Integer> connection : connections) {
                Integer parent = connection.get(0);
                Integer adjacent = connection.get(1);

                List<Integer> adjacents = connectionsMap.get(parent);
                if (adjacents == null) {
                    adjacents = new ArrayList<>();
                    connectionsMap.put(parent, adjacents);
                }
                adjacents.add(adjacent);

                // Create empty adjacents list for non-existing records
                adjacents = connectionsMap.get(adjacent);
                if (adjacents == null) {
                    adjacents = new ArrayList<>();
                    connectionsMap.put(adjacent, adjacents);
                }
            }
        }

        // Walk through adjacents
        List<Integer> visitedIds = new ArrayList<>();
        List<List<Integer>> edges = new ArrayList<>();
        {
            Iterator<Map.Entry<Integer, List<Integer>>> entryIt = connectionsMap.entrySet().iterator();
            List<Integer> adjacents = null;
            Integer node = null;

            while (entryIt.hasNext() && (adjacents == null || adjacents.isEmpty())) {
                Map.Entry<Integer, List<Integer>> entry = entryIt.next();
                connectionsMap.entrySet().iterator().next();
                node = entry.getKey();
                adjacents = entry.getValue();
            }

            Stack<Integer> stack = new Stack<>();
            addToStack(stack, node, visitedIds, connectionsMap);


            while (!stack.isEmpty()) {
                node = stack.pop();
                addToStack(stack, node, visitedIds, connectionsMap);
            }
        }

        // Compute low indexes
        int[] lowIndexes = new int[connectionsMap.size()];
        {
            for (int i = 0; i < lowIndexes.length; i++) {
                lowIndexes[i] = i;
            }
            for (Integer node : connectionsMap.descendingKeySet()) {
                List<Integer> adjacents = connectionsMap.get(node);

                int minVal = lowIndexes[node]; //Integer.MAX_VALUE;
                for (Integer adjacent : adjacents) {
                    if (adjacent == node) {
                        continue;
                    }
//                    if (adjacent < minVal) {
//                        minVal = adjacent;
//                    }
                    if (lowIndexes[adjacent] < minVal) {
                        minVal = lowIndexes[adjacent];
                    }
                }
                lowIndexes[node] = Math.min(node, minVal);
            }
        }

        // Determine critical connections
        {
            for (List<Integer> connection : connections) {
                Integer parent = connection.get(0);
                Integer adjacent = connection.get(1);

                if (parent < lowIndexes[adjacent]) {
                    criticals.add(new ArrayList<>(Arrays.asList(parent, adjacent)));
                }

                parent = connection.get(1);
                adjacent = connection.get(0);

                if (parent < lowIndexes[adjacent]) {
                    criticals.add(new ArrayList<>(Arrays.asList(parent, adjacent)));
                }
            }
        }

        System.out.println(connectionsMap);
        System.out.println(visitedIds);
        System.out.println(Arrays.toString(lowIndexes));
    }

    private List<List<Integer>> addReversePathways(List<List<Integer>> connections) {
        List<List<Integer>> reversed = new ArrayList<>();

        for (List<Integer> connection : connections) {
            int parent = connection.get(0);
            int adjacent = connection.get(1);

            reversed.add(new ArrayList<>(Arrays.asList(parent, adjacent)));
            reversed.add(new ArrayList<>(Arrays.asList(adjacent, parent)));
        }

        return reversed;
    }

    private void addToStack(Stack<Integer> stack, Integer node, List<Integer> visitedIds, Map<Integer, List<Integer>> connectionsMap) {
        if (visitedIds.contains(node)) {
            return;
        }
        visitedIds.add(node);

        List<Integer> adjacents = connectionsMap.get(node);
        if (adjacents != null && adjacents.size() > 0) {
            for (int i = adjacents.size() - 1; i >= 0; i--) {
                int adjacent = adjacents.get(i);
                if (!visitedIds.contains(adjacent)) {
                    stack.add(adjacent);
                }
            }
        }
    }

    public List<List<Integer>> criticalConnection(int n, List<List<Integer>> connections) {
        List<List<Integer>> criticals = new ArrayList<>();

//        connections = addReversePathways(connections);
        findCriticalConnections(connections, criticals);

        return criticals;
    }

    public void run() {
        List<List<Integer>> connections = new ArrayList<>();
//        connections.add(new ArrayList<>(Arrays.asList(0, 1)));
//        connections.add(new ArrayList<>(Arrays.asList(1, 2)));
//        connections.add(new ArrayList<>(Arrays.asList(2, 0)));
//        connections.add(new ArrayList<>(Arrays.asList(2, 3)));
//        connections.add(new ArrayList<>(Arrays.asList(2, 5)));
//        connections.add(new ArrayList<>(Arrays.asList(3, 4)));
//        connections.add(new ArrayList<>(Arrays.asList(5, 6)));
//        connections.add(new ArrayList<>(Arrays.asList(6, 7)));
//        connections.add(new ArrayList<>(Arrays.asList(7, 8)));
//        connections.add(new ArrayList<>(Arrays.asList(8, 5)));

        connections.add(new ArrayList<>(Arrays.asList(1, 0)));
        connections.add(new ArrayList<>(Arrays.asList(2, 0)));
        connections.add(new ArrayList<>(Arrays.asList(3, 2)));
        connections.add(new ArrayList<>(Arrays.asList(4, 2)));
        connections.add(new ArrayList<>(Arrays.asList(4, 3)));
        connections.add(new ArrayList<>(Arrays.asList(3, 0)));
        connections.add(new ArrayList<>(Arrays.asList(4, 0)));

        List<List<Integer>> criticals = criticalConnection(connections.size(), connections);
        System.out.println(criticals);
    }
}
