package leetcode2;

import java.util.*;

class Medium {
    List<List<Integer>> result;

    public List<List<Integer>> findSubsequences(int[] nums) {
        result = new ArrayList<>();
        findAll(nums, 0, new ArrayList<>());
        return result;
    }

    private void findAll(int[] nums, int index, List<Integer> path) {
        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
        }

        if (index == nums.length) {
            return;
        }

        Set<Integer> used = new HashSet<>();

        for (int i = index; i < nums.length; i++) {
            if (used.contains(nums[i])) continue;

            if (path.isEmpty() || nums[i] >= path.get(path.size() - 1)) {
                used.add(nums[i]);
                path.add(nums[i]);
                findAll(nums, i + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }
}