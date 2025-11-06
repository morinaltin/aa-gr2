import java.util.*;

public class WarehouseInventoryOptimizer {

    static class Batch {
        String id;
        int size;
        int priority;

        Batch(String id, int size, int priority) {
            this.id = id;
            this.size = size;
            this.priority = priority;
        }
    }

    static class Result {
        int maxPriority;
        List<String> selectedBatchIds;

        Result(int maxPriority, List<String> selectedBatchIds) {
            this.maxPriority = maxPriority;
            this.selectedBatchIds = selectedBatchIds;
        }

        @Override
        public String toString() {
            return "{ maxPriority: " + maxPriority +
                    ", acceptedBatches: " + selectedBatchIds + " }";
        }
    }

    public static Result optimizeInventory(int shelfCapacity, List<Batch> batches) {
        int batchCount = batches.size();
        int[] bestPriorityAtCapacity = new int[shelfCapacity + 1];
        boolean[][] chosenAtStep = new boolean[batchCount][shelfCapacity + 1];

        for (int index = 0; index < batchCount; index++) {
            Batch batch = batches.get(index);
            for (int capacity = shelfCapacity; capacity >= batch.size; capacity--) {
                int priorityIfTaken = bestPriorityAtCapacity[capacity - batch.size] + batch.priority;
                if (priorityIfTaken > bestPriorityAtCapacity[capacity]) {
                    bestPriorityAtCapacity[capacity] = priorityIfTaken;
                    chosenAtStep[index][capacity] = true;
                }
            }
        }

        List<String> selectedIds = new ArrayList<>();
        int remainingCapacity = shelfCapacity;

        for (int index = batchCount - 1; index >= 0; index--) {
            if (chosenAtStep[index][remainingCapacity]) {
                Batch batch = batches.get(index);
                selectedIds.add(batch.id);
                remainingCapacity -= batch.size;
            }
        }

        Collections.reverse(selectedIds);
        return new Result(bestPriorityAtCapacity[shelfCapacity], selectedIds);
    }

    public static void main(String[] args) {
        List<Batch> batches1 = Arrays.asList(
                new Batch("B001", 10, 60),
                new Batch("B002", 20, 100),
                new Batch("B003", 30, 120),
                new Batch("B004", 15, 80)
        );
        System.out.println(optimizeInventory(50, batches1));

        List<Batch> batches2 = Arrays.asList(
                new Batch("X1", 25, 150),
                new Batch("X2", 40, 200),
                new Batch("X3", 30, 180),
                new Batch("X4", 35, 220),
                new Batch("X5", 20, 100)
        );
        System.out.println(optimizeInventory(100, batches2));
    }
}
