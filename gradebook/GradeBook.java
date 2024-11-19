import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class GradeBook {
    private static Student[] students;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // TODO: initialize students from contents of grades.txt file
        String filename = input.nextLine();
        File f = new File(filename);
        try{
            Scanner fin = new Scanner (f);
            students = new Student[Integer.parseInt(fin.nextLine())];
            for(int i = 0; i < students.length; i++){
                students[i] = new Student();
                String[] inf = fin.nextLine().split(",");
                students[i].setFirstName(inf[0]);
                students[i].setLastName(inf[1]);
                students[i].setGrade(Double.parseDouble(inf[2]));
            }
            fin.close();
        }catch(FileNotFoundException e) {
            System.out.println("Error!! No files available.");
            System.exit(1);
        }
        System.out.println("Welcome to the CM111 Grade Book App!");

        while(true) {
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1) List Class Grades");
            System.out.println("2) Update Grade");
            System.out.println("3) Exit");
            System.out.print("\nPlease choose an option: ");
            String choice = input.nextLine();
            System.out.println();
            switch(choice) {
                case "1": 
                    for(Student student: students) {
                        System.out.printf("%s, %s: %f%n", student.getLastName(), 
                                                        student.getFirstName(), 
                                                        student.getGrade());
                    }
                    break;
                case "2":
                    System.out.println("Enter First Name: ");
                    String fname = input.nextLine();
                    System.out.println("Enter Last Name: ");
                    String lname = input.nextLine();
                    boolean studentboolean = false;
                    for(Student student: students) {
                        if(student.getFirstName().equals(fname) &&
                           student.getLastName().equals(lname)) {
                           System.out.println("Enter Grade: ");
                           student.setGrade(Double.parseDouble(input.nextLine()));
                           System.out.println("Grade updated");
                           studentboolean = true;
                           break;
                        }
                    }
                    if (studentboolean == false){
                        System.out.println("Student not found");
                    }
                    break;
                case "3":
                    // Challenge: write code to save the grades to grades.txt
                    try{
                        PrintWriter fout = new PrintWriter(f);
                        fout.println(students.length);
                        for(Student stu : students){
                            fout.printf("%s,%s,%.0f%n", stu.getFirstName(), stu.getLastName(), stu.getGrade());
                        }
                        fout.close();
                    }catch(FileNotFoundException e) {
                        System.out.println("Error writing to file");
                    }
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }
}
