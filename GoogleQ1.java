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

import java.util.Stack;

public class GoogleQ1 {

    private static int solutions;

    private static void printStack(Stack<Integer> stack) {
        int i = 0;
        for (Integer val : stack) {
            System.out.print(val);
            if (i < stack.size() - 1) {
                System.out.print(" -> ");
            } else {
                System.out.println("");
            }
            i++;
        }
    }

    private static void findWays(int len, Stack<Integer> stack, int limit) {
        int index = stack.peek();
        if (stack.size() == limit + 1) {
            if (index == 0) {
                solutions++;
                printStack(stack);
            }
            return;
        }
        for (int i = -1; i <= 1; i++) {
            if (index + i < 0) {
                continue;
            } else if (index + i > len - 1) {
                continue;
            }
            Stack<Integer> clone = (Stack<Integer>) stack.clone();
            clone.add(index + i);
            findWays(len, clone, limit);
        }
    }

    private void solve(int h, int n) {
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        findWays(h, stack, n);

        System.out.println("Having array of " + h + " length and " + n + " steps to take. " +
                "You can walk through " + solutions + " ways ending up back on index 0 position");
    }

    public void run() {
        solve(10, 6);
    }
}

