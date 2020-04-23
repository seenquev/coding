import com.sun.istack.internal.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class ValuesTracker {
    // For a given signal id we store different values for different value types (max, min, median)
    private final HashMap<Integer, LinkedList<Float>> valuesMap = new HashMap<>();

    private int mCapacity;
    private int mTimeoutMs;
    private long mLastUpdateMs;

    public ValuesTracker(int capacity, int timeoutMs) {
        mCapacity = capacity;
        mTimeoutMs = timeoutMs;
    }

    public void addValues(int signalId, float[] values, long timeMs) {
        if (values.length == 0) {
            return;
        }

        // Find existing data
        LinkedList<Float> valuesList = valuesMap.get(signalId);
        if (valuesList == null) {
            valuesList = new LinkedList<>();
            valuesMap.put(signalId, valuesList);
        }

        clearOutdated(signalId, valuesList, timeMs);

        float prevValue = (valuesList.isEmpty() ? Float.MIN_VALUE : valuesList.getFirst());
        for (int i = 0; i < values.length; i++) {
            Float val = values[i];
            if (prevValue != Integer.MIN_VALUE && Float.compare(prevValue, val) == 0) {
                continue;
            }
            valuesList.addFirst(val);
            if (valuesList.size() > mCapacity) {
                valuesList.removeLast();
            }
            prevValue = val;
        }
    }

    public void addValue(int signalId, float value, long timeMs) {
        // Find existing data
        LinkedList<Float> valuesList = valuesMap.get(signalId);
        if (valuesList == null) {
            valuesList = new LinkedList<>();
            valuesMap.put(signalId, valuesList);
        }

        if (!valuesList.isEmpty() && Float.compare(valuesList.getFirst(), value) == 0) {
            return;
        }

        clearOutdated(signalId, valuesList, timeMs);
        valuesList.addFirst(value);
        if (valuesList.size() > mCapacity) {
            valuesList.removeLast();
        }
    }

    // (int) ret: -1 [invalid], 0 [equal], 1 [decreasing], 2 [increasing]
    public int checkTendency(int signalId, int minSize, float threshold, int errorsAllowed) {
        int ret = -1;
        LinkedList<Float> valuesList = valuesMap.get(signalId);
        if (valuesList == null) {
            return ret;
        }
        if (valuesList.size() < minSize) {
            return ret;
        }
        Float[] diffs = getDiffs(signalId);
        if (diffs == null) {
            return ret;
        }

        int count = 1;
        int ups = 0;
        int downs = 0;
        int errors = 0;
        int equals = 0;
        float totalDiff = 0.0f;
        for (int i = diffs.length - 1; i >= 0; i--) {
            Float diff = diffs[i];
            totalDiff += diff;
            if (Float.compare(diff, 0.0f) == 0) {
                equals++;
            } else if (Float.compare(diff, 0.0f) < 0) {
                downs++;
            } else if (Float.compare(diff, 0.0f) > 0) {
                ups++;
            }

            if (count >= minSize && Math.abs(totalDiff) > threshold) {
                break;
            }
            count++;
        }

        if (threshold > Math.abs(totalDiff)) {
            return ret;
        }

        if (ups > downs && ups > equals) {
            errors = downs;
            ret = 2;
        } else if (downs > ups && downs > equals) {
            errors = ups;
            ret = 1;
        } else if (equals > ups && equals > downs) {
            errors = downs + ups;
            ret = 0;
        }
        if (count > minSize) {
            errorsAllowed *= count / minSize;
        }

        return (errors <= errorsAllowed ? ret : -1);
    }

    public @Nullable Float[] getDiffs(int signalId) {
        // Find existing data
        LinkedList<Float> valuesList = valuesMap.get(signalId);
        if (valuesList == null) {
            return null;
        }
        if (valuesList.size() < 2) {
            return null;
        }

        Float[] diffs = new Float[valuesList.size() - 1];
        int j = 0;
        float refVal = valuesList.get(valuesList.size() - 1);
        for (int i = valuesList.size() - 2; i >= 0; i--) {
            float val = valuesList.get(i);
            diffs[j++] = val - refVal;
            refVal = val;
        }

        return diffs;
    }

    private void clearOutdated(int signalId, LinkedList<Float> valuesList, long timeMs) {
        if (mLastUpdateMs > 0 && timeMs - mLastUpdateMs > mTimeoutMs) {
            valuesList.clear();
        }
        mLastUpdateMs = timeMs;
    }

    public void run() {
        float[] array1 = { 1, 4, 6, 7, 7, 8, 9 };
        addValues(1, array1, 0);

        System.out.println(checkTendency(1 /* signalId */, 5 /* consecutivity */, 5.0f, 1 /* errorsAllowed */));

        float value1 = 9;
        float value2 = 11;
        float[] array2 = { 12, 10, 9, 8, 8, 7 };
        addValue(1, value1, 1);
        addValue(1, value2, 2);
        addValues(1, array2, 3);

        System.out.println(checkTendency(1 /* signalId */, 5 /* consecutivity */, 2.0f, 1 /* errorsAllowed */));
        float[] array3 = { 2, 3, 4, 5, -1, 9 };
        addValues(1, array3, 4);
        System.out.println(checkTendency(1 /* signalId */, 5 /* consecutivity */, 5.0f, 1 /* errorsAllowed */));

//        int[] array3 = { 3, 4, 5, 6, 7, 8 };
//        int[] array4 = { 0, 1, 1, 2, 3, 4 };
//        int[] array5 = { 5, 3, 2, 1, 1, 1 };
//        int[] array6 = { 7, 5, 5, 4, 2, 1 };
//        int[] array7 = { 9, 9, 8, 8, 7, 7 };
//        int[] array8 = { 3, 2, 1, 0, 0, 0 };

        Float[] ret = getDiffs(1);
        System.out.println(Arrays.toString(ret));
    }
};