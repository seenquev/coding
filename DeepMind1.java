// You start at index 0 in an array with length 'h'. At each step, you can move to the left, move to the right, or
// stay in the same place(Note! Stay in the same place also takes one step).
// How many possible ways are you still at index 0 after you have walked 'n' step?
//
//        Example： n = 3
//        1. right->left->stay
//        2. right->stay->left
//        3. stay->right->left
//        4. stay->stay->stay
//
//        Can anyone solve it in n^2

import java.util.*;

/*
 * Given list of tasks and its dependants, figure out the correct order we should execute those tasks.
 */

public class DeepMind1 {

    private static final Map<String, List<String>> TASKS = new TreeMap<String, List<String>>() {
        {
            put("task1", new ArrayList<>(Arrays.asList("task2", "task3")));
            put("task2", new ArrayList<>(Arrays.asList("task3")));
            put("task3", new ArrayList<>());
            put("task4", new ArrayList<>(Arrays.asList("task1")));
            put("task5", new ArrayList<>(Arrays.asList("task4", "task1")));
            put("task6", new ArrayList<>(Arrays.asList("task5")));
        }
    };

    private List<String> getCorrectOrder(Map<String, List<String>> tasks) {
        List<String> retList = new ArrayList<>();
        if (tasks.isEmpty()) {
            return retList;
        }

        Iterator<Map.Entry<String, List<String>>> iterator = tasks.entrySet().iterator();
        Stack<String> stack = new Stack<>();

        while (iterator.hasNext()) {
            String currentTask = iterator.next().getKey();
            stack.push(currentTask);
            System.out.format("Adding %s\n", currentTask);
        }

        while (!stack.empty()) {
            String task = stack.peek();
            System.out.format("Looking at %s\n", task);
            List<String> depTasks = tasks.get(task);

            boolean allValid = false;
            if (depTasks != null) {
                allValid = true;
                for (String depTask : depTasks) {
                    if (!retList.contains(depTask)) {
                        allValid = false;
                        stack.push(depTask);
                    }
                }
            }

            if (depTasks == null || allValid) {
                if (!retList.contains(task)) {
                    retList.add(task);
                }
                stack.pop();
            }
        }

        return retList;
    }

    public void run() {
        System.out.println(TASKS);
        List<String> orderedTasks = getCorrectOrder(TASKS);
        System.out.println(orderedTasks);
    }
}

