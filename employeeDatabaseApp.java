import java.sql.*;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private String email;
    private String country;

    public Employee(int id, String name, String email, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public Employee(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCountry() { return country; }
}

class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employeedb";
    private static final String USER = "root";
    private static final String PASS = "employeepassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}

class EmployeeDAO {
    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, email, country) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getEmail());
            stmt.setString(3, emp.getCountry());
            stmt.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void viewEmployees() {
        String sql = "SELECT * FROM employees";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("ID\tName\tEmail\tCountry");
            while(rs.next()) {
                System.out.printf("%d\t%s\t%s\t%s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("country"));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateEmployee(int id, String name, String email, String country) {
        String sql = "UPDATE employees SET name=?, email=?, country=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, country);
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}


public class employeeDatabaseApp {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("1.Add 2.View 3.Update 4.Delete 5.Exit");
            int choice = sc.nextInt();
            sc.nextLine(); 
            switch(choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter country: ");
                    String country = sc.nextLine();
                    dao.addEmployee(new Employee(name, email, country));
                    break;
                case 2:
                    dao.viewEmployees();
                    break;
                case 3:
                    System.out.print("Enter id to update: ");
                    int upId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String upName = sc.nextLine();
                    System.out.print("Enter new email: ");
                    String upEmail = sc.nextLine();
                    System.out.print("Enter new country: ");
                    String upCountry = sc.nextLine();
                    dao.updateEmployee(upId, upName, upEmail, upCountry);
                    break;
                case 4:
                    System.out.print("Enter id to delete: ");
                    int delId = sc.nextInt();
                    dao.deleteEmployee(delId);
                    break;
                case 5:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
