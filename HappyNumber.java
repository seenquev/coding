import java.util.*;

public class HappyNumber {
    private static int MAX_INT_CHARS = 9;
    private Set<Integer> numbers = new HashSet<>();

    private List<Integer> split(int n) {
        List<Integer> ret = new LinkedList<>();
        for (int i = MAX_INT_CHARS; i >= 0; i--) {
            int div = n / (int)Math.pow(10, i);
            if (div > 0) {
                ret.add((int)div);
                n -= Math.pow(10, i) * div;

                // Fill the rest with zeros
                if (n == 0) {
                    for (int j = i; j > 0; j--) {
                        ret.add(0);
                    }
                }
            }
        }
        return ret;
    }

    private int getNumber(List<Integer> list) {
        int n = 0;
        for (int i = 0; i < list.size(); i++) {
             n += (int) Math.pow(list.get(i), 2);
        }
        return n;
    }

    public boolean isHappy(int n) {
        while (true) {
            if (n == 1) {
                return true;
            }

            if (numbers.contains(n)) {
                return false;
            }
            numbers.add(n);

            List<Integer> list = split(n);
            n = getNumber(list);

            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    public void run() {
        int number = 2147483647;
        boolean ret = isHappy(number);
        System.out.println(ret);
    }
}
