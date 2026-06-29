// Demonstrates the MVC pattern

public class Test {

    public static void main(String[] args) {

        Student student = new Student("Asmitha", 413, "A");

        StudentView view = new StudentView();

        StudentController controller =
                new StudentController(student, view);

        controller.updateView();

        System.out.println();

        controller.setStudentName("Vihan");
        controller.setStudentGrade("A+");

        controller.updateView();

    }

}