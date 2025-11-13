package Detyra7;

public class Employee {
    public int id;
    public String name;
    public String position;
    public int salary;
    public Integer supervisor_id;

    public Employee(int id, String name, String position, int salary, Integer supervisor_id) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.supervisor_id = supervisor_id;
    }
}

