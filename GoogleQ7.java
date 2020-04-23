import java.util.*;

public class GoogleQ7 {

    private static class WorkerBike {
        public int workerId;
        public int bikeId;

        public WorkerBike(int workerId, int bikeId) {
            this.workerId = workerId;
            this.bikeId = bikeId;
        }
    };

    private static int computeDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    private List<Integer> removeValue(List<Integer> list, int value) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) == value) {
                list.remove(i);
                return list;
            }
        }
        return null;
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        SortedMap<Integer, List<WorkerBike>> workerBikePairs = new TreeMap<>();
        List<Integer> workersL = new ArrayList<>();
        List<Integer> bikesL = new ArrayList<>();

        // Let's compute list with closest workers per bike
        for (int j = 0; j < bikes.length; ++j) {
            bikesL.add(j);
            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < workers.length; ++i) {
                if (!workersL.contains(i)) {
                    workersL.add(i);
                }
                int distance = computeDistance(workers[i], bikes[j]);

                List<WorkerBike> workerBike = workerBikePairs.get(distance);
                if (workerBike == null) {
                    workerBike = new ArrayList<>();
                }

                workerBike.add(new WorkerBike(i, j));
                workerBikePairs.put(distance, workerBike);
            }
        }

        // Let's pick best bike for a worker
        int[] ret = new int[workers.length];

        for (List<WorkerBike> workerBikeL : workerBikePairs.values()) {
            for (WorkerBike workerBike : workerBikeL) {
                if (workersL.contains(workerBike.workerId) && bikesL.contains(workerBike.bikeId)) {
                    ret[workerBike.workerId] = workerBike.bikeId;

                    workersL = removeValue(workersL, workerBike.workerId);
                    bikesL = removeValue(bikesL, workerBike.bikeId);
                }
            }
        }

        return ret;
    }

    public void run() {
        int[][] workers = { {0, 0}, {2, 1} };
        int[][] bikes = { {1, 2}, {3, 3} };

        int[] ret = assignBikes(workers, bikes);
        System.out.println(Arrays.toString(ret));
    }
}
