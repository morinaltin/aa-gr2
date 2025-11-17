package Detyra7;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = loadEmployees();

        System.out.println("==============================================");
        System.out.println("          PROBLEM 1: EMPLOYEE DEPTH");
        System.out.println("==============================================");
        System.out.println("Depth of 1: " + OrgUtils.getEmployeeDepth(employees, 1));
        System.out.println("Depth of 2: " + OrgUtils.getEmployeeDepth(employees, 2));
        System.out.println("Depth of 7: " + OrgUtils.getEmployeeDepth(employees, 7));
        System.out.println("Depth of 12: " + OrgUtils.getEmployeeDepth(employees, 12));
        System.out.println("Depth of 999: " + OrgUtils.getEmployeeDepth(employees, 999));
        System.out.println();

        System.out.println("==============================================");
        System.out.println("       PROBLEM 2: ALL SUBORDINATES (DFS)");
        System.out.println("==============================================");
        System.out.println("Subordinates of 2:");
        for (Subordinate s : OrgUtils.getAllSubordinates(employees, 2)) {
            System.out.println(s.id + " | " + s.name + " | depth=" + s.relative_depth);
        }
        System.out.println();

        System.out.println("Subordinates of 6:");
        for (Subordinate s : OrgUtils.getAllSubordinates(employees, 6)) {
            System.out.println(s.id + " | " + s.name + " | depth=" + s.relative_depth);
        }
        System.out.println();

        System.out.println("Subordinates of 12:");
        for (Subordinate s : OrgUtils.getAllSubordinates(employees, 12)) {
            System.out.println(s.id + " | " + s.name + " | depth=" + s.relative_depth);
        }
        System.out.println();

        System.out.println("==============================================");
        System.out.println("         PROBLEM 3: PATH TO CEO");
        System.out.println("==============================================");
        System.out.println("Path for 12: " + OrgUtils.getPathToCeo(employees, 12));
        System.out.println("Path for 1: " + OrgUtils.getPathToCeo(employees, 1));
        System.out.println("Path for 5: " + OrgUtils.getPathToCeo(employees, 5));
        System.out.println("Path for 999: " + OrgUtils.getPathToCeo(employees, 999));
        System.out.println();

        System.out.println("==============================================");
        System.out.println("       PROBLEM 4: LOWEST COMMON MANAGER");
        System.out.println("==============================================");

        CommonManager c1 = OrgUtils.findLowestCommonManager(employees, 12, 9);
        System.out.println("LCA of 12 & 9: " + c1.manager_name + " (ID " + c1.manager_id +
                ", dist1=" + c1.distance_to_emp1 + ", dist2=" + c1.distance_to_emp2 + ")");

        CommonManager c2 = OrgUtils.findLowestCommonManager(employees, 8, 10);
        System.out.println("LCA of 8 & 10: " + c2.manager_name + " (ID " + c2.manager_id +
                ", dist1=" + c2.distance_to_emp1 + ", dist2=" + c2.distance_to_emp2 + ")");

        CommonManager c3 = OrgUtils.findLowestCommonManager(employees, 1, 12);
        System.out.println("LCA of 1 & 12: " + c3.manager_name + " (ID " + c3.manager_id +
                ", dist1=" + c3.distance_to_emp1 + ", dist2=" + c3.distance_to_emp2 + ")");
        System.out.println();

        System.out.println("==============================================");
        System.out.println("       PROBLEM 5: TEAM SALARY (Memoized)");
        System.out.println("==============================================");

        Map<Integer, Integer> memo = new HashMap<>();
        System.out.println("Team salary for 1: " + OrgUtils.calculateTeamSalary(employees, 1, memo));
        System.out.println("Team salary for 4: " + OrgUtils.calculateTeamSalary(employees, 4, memo));
        System.out.println("Team salary for 7: " + OrgUtils.calculateTeamSalary(employees, 7, memo));
        System.out.println("Team salary for 12: " + OrgUtils.calculateTeamSalary(employees, 12, memo));

        System.out.println("\nMemoized results: " + memo);
        System.out.println();
    }

    public static List<Employee> loadEmployees() {
        return List.of(
                new Employee(1, "Alice Johnson", "CEO", 250000, null),
                new Employee(2, "Bob Smith", "CTO", 180000, 1),
                new Employee(3, "Carol White", "CFO", 175000, 1),
                new Employee(4, "David Brown", "Engineering Manager", 140000, 2),
                new Employee(5, "Eve Davis", "QA Manager", 130000, 2),
                new Employee(6, "Frank Wilson", "Senior Accountant", 95000, 3),
                new Employee(7, "Grace Lee", "Senior Developer", 120000, 4),
                new Employee(8, "Henry Martinez", "Junior Developer", 85000, 4),
                new Employee(9, "Ivy Chen", "QA Engineer", 90000, 5),
                new Employee(10, "Jack Thompson", "DevOps Engineer", 110000, 4),
                new Employee(11, "Kelly Anderson", "Junior Accountant", 65000, 6),
                new Employee(12, "Liam Garcia", "Intern Developer", 50000, 7)
        );
    }
}
