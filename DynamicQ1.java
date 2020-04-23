import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicQ1 {

    private boolean checkSum(int[] ptrs, int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < ptrs.length; i++) {
            sum += nums[ptrs[i]];
        }
        return sum == target;
    }

    private boolean incrementPtrs(int level, int[] ptrs, int length) {
        if (level == ptrs.length) {
            return false;
        }

        int i = ptrs.length - level - 1;

        if (ptrs[i] == length - level - 1) {
            return incrementPtrs(level + 1, ptrs, length);
        }
        ptrs[i]++;

        // Adjust other pointers
        int k = 1;
        for (int j = ptrs.length - level; j < ptrs.length; j++) {
            ptrs[j] = ptrs[i] + k;
            k++;
        }

        return true;
    }

    private boolean contains(List<Integer> list,  List<List<Integer>> retList) {

        for (List<Integer> compList : retList) {
            List<Integer> copyList = new ArrayList<>(list);
            for (int i = 0; i < compList.size(); i++) {
                int value = compList.get(i);
                int index = copyList.indexOf(value);

                if (index != -1) {
                    copyList.remove(index);
                } else {
                    break;
                }
            }
            if (copyList.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> retList = new ArrayList<>();
        if (nums.length < 4) {
            return retList;
        }

        int[] ptrs = new int[4];
        for (int i = 0; i < ptrs.length; i++) {
            ptrs[i] = i;
        }

        boolean proceed = true;
        while (proceed) {
            if (checkSum(ptrs, nums, target)) {
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < ptrs.length; j++) {
                    list.add(nums[ptrs[j]]);
                }

                if (!contains(list, retList)) {
                    retList.add(list);
                }
            }

            proceed = incrementPtrs(0, ptrs, nums.length);
        }

        return retList;
    }

    public void run() {
//        int[] array = {1, 0, -1, 0, -2, 2};
//        int[] array = {-3,-2,-1,0,0,1,2,3 };
        int[] array = {-5,5,4,-3,0,0,4,-2};
        List<List<Integer>> retList = fourSum(array, 4);
        System.out.println(Arrays.toString(retList.toArray()));
    }
}
