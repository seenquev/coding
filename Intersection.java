import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        List<Integer> retList = new ArrayList<>();

        // Sort both arrays
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int ptr1 = 0;
        int ptr2 = 0;
        while (true) {
            int val1 = nums1[ptr1];
            int val2 = nums2[ptr2];

            if (val1 == val2 && !retList.contains(val1)) {
                retList.add(val1);
            }

            if (val1 < val2 && ptr1 < nums1.length - 1) {
                ptr1++;
            } else if (ptr2 < nums2.length - 1) {
                ptr2++;
            } else {
                if (ptr1 < nums1.length - 1) {
                    ptr1++;
                } else if (ptr2 < nums2.length - 1) {
                    ptr2++;
                } else {
                    break;
                }
            }
        }

        return retList.stream().mapToInt(i->i).toArray();
    }

    public void run() {
//        int[] nums1 = {4, 9, 5};
//        int[] nums2 = {9, 4, 9, 8, 4};

//        int[] nums1 = {1, 2, 2, 1};
//        int[] nums2 = {2, 2};

        int[] nums1 = {2, 1};
        int[] nums2 = {1, 2};

        int[] intersect = intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersect));
    }
}
