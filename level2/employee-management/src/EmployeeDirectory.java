import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory {
    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        if (find(employee.getId()) != null) {
            throw new IllegalArgumentException("Employee id " + employee.getId() + " already exists.");
        }
        employees.add(employee);
    }

    public List<Employee> all() {
        return List.copyOf(employees);
    }

    public Employee find(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public void update(int id, String name, String department, double salary) {
        Employee employee = require(id);
        employee.setName(name);
        employee.setDepartment(department);
        employee.setSalary(salary);
    }

    public void delete(int id) {
        if (!employees.removeIf(employee -> employee.getId() == id)) {
            throw new IllegalArgumentException("No employee with id " + id + ".");
        }
    }

    private Employee require(int id) {
        Employee employee = find(id);
        if (employee == null) {
            throw new IllegalArgumentException("No employee with id " + id + ".");
        }
        return employee;
    }
}
