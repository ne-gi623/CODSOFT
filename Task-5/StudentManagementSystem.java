import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(int rollNumber, String name, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNumber + ", " + name + ", " + grade;
    }

    public String toFileString() {
        return rollNumber + "," + name + "," + grade;
    }
}

public class StudentManagementSystem {

    private static final String FILE_NAME = "students.txt";
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadFromFile();

        int choice;
        do {
            System.out.println("\n STUDENT MANAGEMENT SYSTEM ");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> searchStudent();
                case 4 -> editStudent();
                case 5 -> displayAllStudents();
                case 6 -> {
                    saveToFile();
                    System.out.println(" Exiting... Data saved.");
                }
                default -> System.out.println(" Invalid choice.");
            }
        } while (choice != 6);
    }

    // Add student
    private static void addStudent() {
        System.out.print("Enter roll number: ");
        int roll = scanner.nextInt();
        scanner.nextLine();

        if (findStudent(roll) != null) {
            System.out.println(" Student with this roll number already exists.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine().trim();

        if (name.isEmpty() || grade.isEmpty()) {
            System.out.println(" Fields cannot be empty.");
            return;
        }

        students.add(new Student(roll, name, grade));
        System.out.println(" Student added successfully.");
    }

    // Remove student
    private static void removeStudent() {
        System.out.print("Enter roll number to remove: ");
        int roll = scanner.nextInt();

        Student s = findStudent(roll);
        if (s != null) {
            students.remove(s);
            System.out.println(" Student removed.");
        } else {
            System.out.println(" Student not found.");
        }
    }

    // Search student
    private static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        int roll = scanner.nextInt();

        Student s = findStudent(roll);
        if (s != null) {
            System.out.println(" Found: " + s);
        } else {
            System.out.println(" Student not found.");
        }
    }

    // Edit student
    private static void editStudent() {
        System.out.print("Enter roll number to edit: ");
        int roll = scanner.nextInt();
        scanner.nextLine();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println(" Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new grade: ");
        String grade = scanner.nextLine();

        if (!name.isEmpty()) s.setName(name);
        if (!grade.isEmpty()) s.setGrade(grade);

        System.out.println(" Student updated successfully.");
    }

    // Display all students
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println(" No students available.");
            return;
        }

        System.out.println("\n Student List:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Helper: find student
    private static Student findStudent(int roll) {
        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                return s;
            }
        }
        return null;
    }

    // Load data from file
    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2]
                ));
            }
        } catch (Exception e) {
            System.out.println(" Error loading file.");
        }
    }

    // Save data to file
    private static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println(" Error saving file.");
        }
    }
}
