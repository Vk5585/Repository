package student_record_management_system.java;

import java.util.Scanner;
import java.util.ArrayList;

class Student {
    int id;
    String name;
    double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

public class student_record_management_system {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(int id, String name, double marks) {
        students.add(new Student(id, name, marks));
        System.out.println("Student added.");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void updateStudent(int id, String newName, double newMarks) {
        for (Student s : students) {
            if (s.id == id) {
                s.name = newName;
                s.marks = newMarks;
                System.out.println("Student updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudent(int id) {
        for (Student s : students) {
            if (s.id == id) {
                students.remove(s);
                System.out.println("Student id deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void main(String[] args) {
        student_record_management_system manager = new student_record_management_system();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.Add\n2.View\n3.Update\n4.Delete\n5.Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    manager.addStudent(id, name, marks);
                    break;
                case 2:
                    manager.viewStudents();
                    break;
                case 3:
                    System.out.print("Enter ID to update: ");
                    id = scanner.nextInt();
                    System.out.print("Enter new Name: ");
                    name = scanner.next();
                    System.out.print("Enter new Marks: ");
                    marks = scanner.nextDouble();
                    manager.updateStudent(id, name, marks);
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    id = scanner.nextInt();
                    manager.deleteStudent(id);
                    break;
                case 5:
                    System.out.println("Exiting..!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}