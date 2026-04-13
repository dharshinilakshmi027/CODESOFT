import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n------- Welcome to \"Student Grade Calculator\" -------");

        int totalMarks = 0;
        float avgPercentage;

        System.out.print("\nEnter the total number of subjects: ");
        int subjects = sc.nextInt();

        // Validate number of subjects
        if (subjects <= 0) {
            System.out.println("Error: Subjects must be greater than 0.");
            sc.close();
            return;
        }

        // Input marks for each subject
        for (int i = 1; i <= subjects; i++) {

            System.out.printf("Enter marks of Subject %d (Out of 100): ", i);
            int marks = sc.nextInt();

            // Validate marks
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid input! Marks must be between 0 and 100.\n");
                i--; // repeat same subject
                continue;
            }

            totalMarks += marks;
        }

        // Display total
        System.out.println("\nTotal Marks = " + totalMarks);

        // Calculate average
        avgPercentage = (float) totalMarks / subjects;
        System.out.printf("Average Percentage = %.2f%%\n", avgPercentage);

        // Assign grade
        if (avgPercentage >= 90) {
            System.out.println("Grade: A");
        } else if (avgPercentage >= 80) {
            System.out.println("Grade: B");
        } else if (avgPercentage >= 70) {
            System.out.println("Grade: C");
        } else if (avgPercentage >= 60) {
            System.out.println("Grade: D");
        } else if (avgPercentage >= 50) {
            System.out.println("Grade: E");
        } else {
            System.out.println("Grade: F (You need improvement)");
        }

        sc.close();
    }
}
