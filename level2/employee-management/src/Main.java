import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDirectory directory = new EmployeeDirectory();
        System.out.println("Employee Management");
        System.out.println("Enter 6 to exit.");
        boolean running = true;
        while (running) {
            printMenu();
            switch (readChoice(scanner)) {
                case 1 -> addEmployee(scanner, directory);
                case 2 -> viewEmployees(directory);
                case 3 -> searchEmployee(scanner, directory);
                case 4 -> updateEmployee(scanner, directory);
                case 5 -> deleteEmployee(scanner, directory);
                case 6 -> {
                    running = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Choose a number from 1 to 6.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add employee");
        System.out.println("2. View all employees");
        System.out.println("3. Search by id");
        System.out.println("4. Update employee");
        System.out.println("5. Delete employee");
        System.out.println("6. Exit");
        System.out.print("Choice: ");
        System.out.flush();
    }

    private static void addEmployee(Scanner scanner, EmployeeDirectory directory) {
        int id = readInt(scanner, "Employee id: ");
        if (id <= 0) {
            System.out.println("Employee id must be greater than zero.");
            return;
        }
        if (directory.find(id) != null) {
            System.out.println("Employee id " + id + " already exists.");
            return;
        }
        String name = readText(scanner, "Name: ");
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        String department = readText(scanner, "Department: ");
        if (department.isEmpty()) {
            System.out.println("Department cannot be empty.");
            return;
        }
        double salary = readDouble(scanner, "Salary amount: ");
        if (salary < 0) {
            System.out.println("Salary cannot be negative.");
            return;
        }
        directory.add(new Employee(id, name, department, salary));
        System.out.println("Employee added.");
        System.out.println(directory.find(id).describe());
    }

    private static void viewEmployees(EmployeeDirectory directory) {
        List<Employee> employees = directory.all();
        if (employees.isEmpty()) {
            System.out.println("No employees yet.");
            return;
        }
        System.out.println("Employees:");
        for (Employee employee : employees) {
            System.out.println(employee.describe());
        }
    }

    private static void searchEmployee(Scanner scanner, EmployeeDirectory directory) {
        int id = readInt(scanner, "Employee id: ");
        Employee employee = directory.find(id);
        if (employee == null) {
            System.out.println("No employee with id " + id + ".");
            return;
        }
        System.out.println(employee.describe());
    }

    private static void updateEmployee(Scanner scanner, EmployeeDirectory directory) {
        int id = readInt(scanner, "Employee id: ");
        if (directory.find(id) == null) {
            System.out.println("No employee with id " + id + ".");
            return;
        }
        String name = readText(scanner, "Name: ");
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        String department = readText(scanner, "Department: ");
        if (department.isEmpty()) {
            System.out.println("Department cannot be empty.");
            return;
        }
        double salary = readDouble(scanner, "Salary amount: ");
        if (salary < 0) {
            System.out.println("Salary cannot be negative.");
            return;
        }
        directory.update(id, name, department, salary);
        System.out.println("Employee updated.");
        System.out.println(directory.find(id).describe());
    }

    private static void deleteEmployee(Scanner scanner, EmployeeDirectory directory) {
        int id = readInt(scanner, "Employee id: ");
        try {
            directory.delete(id);
            System.out.println("Employee deleted.");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int readChoice(Scanner scanner) {
        while (true) {
            String line = readRequiredLine(scanner);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a whole number.");
                System.out.print("Choice: ");
                System.out.flush();
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.flush();
            String line = readRequiredLine(scanner);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a whole number.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.flush();
            String line = readRequiredLine(scanner);
            try {
                double value = Double.parseDouble(line);
                if (Double.isNaN(value) || Double.isInfinite(value)) {
                    System.out.println("Enter a valid number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        System.out.flush();
        return readRequiredLine(scanner);
    }

    private static String readRequiredLine(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            System.out.println();
            System.out.println("Input ended.");
            System.exit(0);
        }
        return scanner.nextLine().trim();
    }
}
