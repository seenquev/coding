/*Given an array of integers, find the second max using only the function compare(a, b)
that compares two integers and retunrs the maximum of the two. The solution must use the
function compare(a, b) minimum number of times.*/

public class GoogleQ2 {

    private int compare(int num1, int num2) {
        return Math.max(num1, num2);
    }

    private int getSecondMax(int[] array) {
        int secondMax = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < array.length - 1; ++i) {
            int result = compare(array[i], array[i + 1]);
            if (result > max) {
                if (max > secondMax) {
                    secondMax = max;
                }
                max = result;
            }
            int smallerNumber = (array[i] != result ? array[i] : array[i + 1]);
            if (smallerNumber > secondMax) {
                secondMax = smallerNumber;
            }
        }

        return secondMax;
    }

    public void run() {
//        int[] array = {5, 3, 6, 8, 3, 6, 2, 9, 1, 0, 2};
        int[] array = {1000, 8, 15, 12, 6, 20, 20, 1};
        int secondMax = getSecondMax(array);

        System.out.println("Second max is: " + secondMax);
    }
}
