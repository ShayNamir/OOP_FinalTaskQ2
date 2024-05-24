import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 Facade class for the Student class
 */
public class StudentFacade {
    /*
        * This method adds a course to the student's cart
     */
    public void addToCart(Course course, Student student){
        // Check if the course is already in the student's cart
        Set<Course> cart = student.getCart();
        if (cart.contains(course)) {
            System.out.println("This course ("+course.getName()+") is already in your cart.");
            return;
        }
        // Check if the course has space for a new student
        if (!course.hasSpace()) {
            System.out.println("This course ("+course.getName()+") is full. You cannot add it to your cart.");
            System.out.println("Do you want to get notified when a spot is available? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equals("yes")) {
                course.addToNotifList(student);
                System.out.println("You will be notified when a spot is available.");
            }
            return;
        }

        // If both conditions are met, add the course to the cart
        cart.add(course);
        course.register();// Update the number of registered students in the course
        System.out.println("Course: "+course.getName()+" added to your cart successfully.");
    }

/*
    * This method removes a course from the student's cart
 */
    public void removeFromCart(Course course, Student student){
        Set<Course> cart = student.getCart();
        // Check if the course is in the cart
        if (!cart.contains(course)) {
            System.out.println("This course is not in your cart.");
            return;
        }
        // Remove the course from the cart
        cart.remove(course);
        System.out.println("Course: "+course.getName()+" removed from your cart successfully.");
        course.unRegistered();// Update the observer of the course
    }
}
