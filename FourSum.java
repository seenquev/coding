import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    private void count4Sum(int level, int[] array, int target, List<List<Integer>> list) {
        
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> retList = new ArrayList<>();
        count4Sum(0, nums, target, retList);
        return retList;
    }

    public void run() {
        int[] array = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> retList = fourSum(array, 0);
        System.out.println(Arrays.toString(retList.toArray()));
    }
}
