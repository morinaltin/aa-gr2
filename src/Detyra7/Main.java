package Detyra7;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Employee> employees = loadEmployees();

        System.out.println(OrgUtils.getEmployeeDepth(employees, 1));
        System.out.println(OrgUtils.getEmployeeDepth(employees, 2));
        System.out.println(OrgUtils.getEmployeeDepth(employees, 7));
        System.out.println(OrgUtils.getEmployeeDepth(employees, 12));
        System.out.println(OrgUtils.getEmployeeDepth(employees, 999));

        System.out.println("Subordinates of 2:");
        for (Subordinate s : OrgUtils.getAllSubordinates(employees, 2)) {
            System.out.println(s.id + " " + s.name + " depth=" + s.relative_depth);
        }

        System.out.println("Subordinates of 6:");
        for (Subordinate s : OrgUtils.getAllSubordinates(employees, 6)) {
            System.out.println(s.id + " " + s.name + " depth=" + s.relative_depth);
        }

        System.out.println("Subordinates of 12:");
        for (Subordinate s : OrgUtils.getAllSubordinates(employees, 12)) {
            System.out.println(s.id + " " + s.name + " depth=" + s.relative_depth);
        }
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
