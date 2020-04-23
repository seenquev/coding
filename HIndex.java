import java.util.*;

public class HIndex {
    public int hIndex(int[] citations) {
        if (citations.length == 0) {
            return 0;
        } else if (citations.length == 1) {
            return Math.min(citations[0], 1);
        }

        Arrays.sort(citations);

        int hIndex = 0;
        int size = citations.length;
        int prevVal = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            int val = Math.min(citations[i], i + 1);
            int rem = size - (i + 1);

            if (hIndex < val && size - val >= rem && prevVal > val) {
                hIndex = val;
            } else {
                return hIndex;
            }
            prevVal = citations[i];
        }

        return hIndex;
    }

    public void run() {
        int[] citations = {3,0,6,1,5};
//        int[] citations = {0, 1};
//        int[] citations = {1, 2};
//        int[] citations = {11, 15};
        int ret = hIndex(citations);
        System.out.println(ret);
    }
}
