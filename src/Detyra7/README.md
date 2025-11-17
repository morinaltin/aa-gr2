~~# Organizational Hierarchy Analysis – Java Implementation

This project implements recursive, DFS-based, and memoized algorithms to analyze an organizational hierarchy.  
Each employee references their supervisor, forming a tree structure similar to real-world organizational charts.

The project solves **five core problems** using techniques such as recursion, depth-first search (DFS), path analysis,  
lowest common ancestor (LCA), and dynamic programming with memoization.

---

## 📌 Dataset Description

Each employee contains:

- `id`
- `name`
- `position`
- `salary`
- `supervisor_id` → `null` for the CEO

The dataset is defined in `Main.loadEmployees()` and includes 12 employees.

---

# 🔷 Problem 1: Employee Depth

Determine the hierarchical depth of an employee.

### Requirements
- CEO depth = **0**
- Direct reports = **1**
- Use recursion
- Detect cycles
- Return `-1` for invalid IDs

### Sample Output
getEmployeeDepth(1) → 0
getEmployeeDepth(2) → 1
getEmployeeDepth(7) → 3
getEmployeeDepth(12) → 4
getEmployeeDepth(999) → -1
---

# 🔷 Problem 2: Get All Subordinates (DFS)

Retrieve all employees who report (directly or indirectly) to a manager.

### Features
- DFS recursion
- Computes `relative_depth`
- Sorted by:
    1. depth (ascending)
    2. id (ascending)

### Example Output (Manager 2)
4 David Brown depth=1
5 Eve Davis depth=1
7 Grace Lee depth=2
8 Henry Martinez depth=2
9 Ivy Chen depth=2
10 Jack Thompson depth=2
12 Liam Garcia depth=3

---

# 🔷 Problem 3: Path to CEO

Return the chain from an employee up to the CEO.

### Example Paths
getPathToCeo(12) → [12, 7, 4, 2, 1]
getPathToCeo(1) → [1]
getPathToCeo(5) → [5, 2, 1]
getPathToCeo(999) → []

---

# 🔷 Problem 4: Lowest Common Manager (LCA)

Find the closest shared manager for two employees.

### Approach
- Get both paths to CEO
- Compare from the end until paths diverge
- Last matching node = LCA

### Example Output
findLowestCommonManager(12, 9)
→ manager_id: 2 (Bob Smith)
distance_to_emp1: 3
distance_to_emp2: 2

Another example:
findLowestCommonManager(8, 10)
→ manager_id: 4 (David Brown)
distance_to_emp1: 1
distance_to_emp2: 1

---

# 🔷 Problem 5: Team Salary (Memoization)

Calculate the total salary of a manager and their entire subtree.

### Features
- DFS for tree traversal
- Memoization to avoid recalculation
- Supports repeated calls efficiently

### Example Outputs
calculateTeamSalary(1) → 1490000
calculateTeamSalary(4) → 505000
calculateTeamSalary(7) → 170000
calculateTeamSalary(12) → 50000

Memoized values are stored in a HashMap.

---

# ▶ Running the Program

Running `Main.java` prints well-formatted results for all tasks:

==============================================
PROBLEM 1: EMPLOYEE DEPTH
Depth of 1: 0
Depth of 2: 1
Depth of 7: 3
Depth of 12: 4
Depth of 999: -1

Copy code
==============================================
PROBLEM 2: ALL SUBORDINATES (DFS)
4 | David Brown | depth=1
5 | Eve Davis | depth=1
...
12 | Liam Garcia | depth=3

Copy code
==============================================
PROBLEM 3: PATH TO CEO
Path for 12: [12, 7, 4, 2, 1]

Copy code
==============================================
PROBLEM 4: LOWEST COMMON MANAGER
LCA of 12 & 9 → Bob Smith (ID 2)

Copy code
==============================================
PROBLEM 5: TEAM SALARY (Memoized)
Team salary for 1: 1490000
Memoized results: { ... }

---

# 🧪 Technologies Used

- Java 17+
- Recursion
- Depth-First Search (DFS)
- Memoization / Dynamic Programming
- Collections: HashMap, ArrayList, List
