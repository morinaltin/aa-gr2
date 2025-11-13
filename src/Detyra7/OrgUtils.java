package Detyra7;

import java.util.*;

public class OrgUtils {

    public static int getEmployeeDepth(List<Employee> employees, int employeeId) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) map.put(e.id, e);

        Set<Integer> visited = new HashSet<>();
        return depthRecursive(map, employeeId, visited);
    }

    private static int depthRecursive(Map<Integer, Employee> map, int id, Set<Integer> visited) {

        if (!map.containsKey(id)) return -1;
        if (visited.contains(id)) return -1;

        visited.add(id);
        Employee e = map.get(id);

        if (e.supervisor_id == null) return 0;

        int parentDepth = depthRecursive(map, e.supervisor_id, visited);
        if (parentDepth == -1) return -1;

        return parentDepth + 1;
    }

    public static List<Subordinate> getAllSubordinates(List<Employee> employees, int managerId) {

        Map<Integer, List<Employee>> childrenMap = new HashMap<>();
        for (Employee e : employees) {
            if (e.supervisor_id != null) {
                childrenMap.computeIfAbsent(e.supervisor_id, k -> new ArrayList<>()).add(e);
            }
        }

        List<Subordinate> result = new ArrayList<>();

        dfsCollect(childrenMap, managerId, 1, result);

        result.sort(Comparator
                .comparingInt((Subordinate s) -> s.relative_depth)
                .thenComparingInt(s -> s.id));

        return result;
    }

    private static void dfsCollect(Map<Integer, List<Employee>> map, int currentId, int depth, List<Subordinate> result) {

        if (!map.containsKey(currentId)) return;

        for (Employee child : map.get(currentId)) {
            result.add(new Subordinate(child.id, child.name, depth));
            dfsCollect(map, child.id, depth + 1, result);
        }
    }

}

