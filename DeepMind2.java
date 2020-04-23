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

import com.sun.istack.internal.Nullable;

import java.util.*;
import java.util.stream.Collectors;

/*
 * Given n bridges and n scouts investigating those bridges, find the shortest one, assuming that scout's sight is limited
 * and can see only m fields in front of him. You're being provided two methods: getSegmentLength(), goToNextSegment().
 */

public class DeepMind2 {

    private static final List<Bridge> BRIDGES = new ArrayList<Bridge>() {
        {
            add(new Bridge(1, Arrays.asList(new Segment(5), new Segment(3), new Segment(1))));
            add(new Bridge(2, Arrays.asList(new Segment(1), new Segment(4), new Segment(5))));
            add(new Bridge(3, Arrays.asList(new Segment(3), new Segment(3))));
            add(new Bridge(4, Arrays.asList(new Segment(1), new Segment(1), new Segment(1), new Segment(2))));

        }
    };

    @Nullable
    private  Bridge getShortestBridge(List<Bridge> bridges) {
        return null;
    }

    public void run() {
        String listContent = BRIDGES.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        System.out.println(listContent);

        Bridge bridge = getShortestBridge(BRIDGES);

        if (bridge != null) {
            System.out.format("Shortest bridge: %d\n", bridge.getId());
        } else {
            System.out.format("There is no shortest bridge");
        }
    }

    private static class Segment {
        private int mLength;

        public Segment(int length) {
            mLength = length;
        }

        public int getLength() {
            return mLength;
        }

        @Override
        public String toString() {
            return String.format("length: %d", mLength);
        }
    }

    private static class Bridge {
        private final List<Segment> mSegments;
        private final int mId;

        public Bridge(int id, List<Segment> segments) {
            mId = id;
            mSegments = segments;
        }

        public List<Segment> getSegments() {
            return mSegments;
        }

        public int getId() {
            return mId;
        }

        @Override
        public String toString() {
            String listContent = mSegments.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            return String.format("id: %d, segments: [%s]", mId, listContent);
        }
    }
}

