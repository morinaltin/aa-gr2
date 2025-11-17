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

    public static List<Integer> getPathToCeo(List<Employee> employees, int employeeId) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) map.put(e.id, e);

        if (!map.containsKey(employeeId)) return List.of();

        List<Integer> path = new ArrayList<>();
        buildPath(map, employeeId, path);
        return path;
    }

    private static void buildPath(Map<Integer, Employee> map, int currentId, List<Integer> path) {
        path.add(currentId);
        Employee e = map.get(currentId);
        if (e.supervisor_id != null) {
            buildPath(map, e.supervisor_id, path);
        }
    }

    public static CommonManager findLowestCommonManager(List<Employee> employees, int emp1, int emp2) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) map.put(e.id, e);

        if (!map.containsKey(emp1) || !map.containsKey(emp2)) return null;

        List<Integer> path1 = getPathToCeo(employees, emp1);
        List<Integer> path2 = getPathToCeo(employees, emp2);

        int i = path1.size() - 1;
        int j = path2.size() - 1;
        int lca = -1;

        while (i >= 0 && j >= 0 && path1.get(i).equals(path2.get(j))) {
            lca = path1.get(i);
            i--;
            j--;
        }

        if (lca == -1) return null;

        Employee m = map.get(lca);
        int dist1 = path1.indexOf(lca);
        int dist2 = path2.indexOf(lca);

        return new CommonManager(m.id, m.name, dist1, dist2);
    }

    public static int calculateTeamSalary(List<Employee> employees,
                                          int managerId,
                                          Map<Integer, Integer> memo) {

        Map<Integer, List<Employee>> children = new HashMap<>();
        Map<Integer, Employee> map = new HashMap<>();

        for (Employee e : employees) {
            map.put(e.id, e);
            if (e.supervisor_id != null) {
                children.computeIfAbsent(e.supervisor_id, k -> new ArrayList<>()).add(e);
            }
        }

        return salaryDfs(managerId, map, children, memo);
    }

    private static int salaryDfs(int id,
                                 Map<Integer, Employee> map,
                                 Map<Integer, List<Employee>> children,
                                 Map<Integer, Integer> memo) {

        if (!map.containsKey(id)) return 0;
        if (memo.containsKey(id)) return memo.get(id);

        int total = map.get(id).salary;

        if (children.containsKey(id)) {
            for (Employee sub : children.get(id)) {
                total += salaryDfs(sub.id, map, children, memo);
            }
        }

        memo.put(id, total);
        return total;
    }




}

