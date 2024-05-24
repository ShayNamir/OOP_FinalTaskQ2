import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Student extends User {
    private Set<Course> cart;
    private final StudentFacade studentFacade = new StudentFacade();//Facade for the Student class

    // Constructor
    public Student(String name, String id, String password) {
        super(name, id, password);
        this.cart = new HashSet<>();// Initialize the cart as an empty set
    }
    public String getId() {
        return super.getId();
    }
    public String getName() {
        return super.getName();
    }

    @Override
    void printDetails() {
        System.out.println("Student ID: " + getId());
        System.out.println("Student Name: " + getName());
        System.out.println("Courses in cart: ");
        for (Course course : cart) {
            System.out.println(course.getName());
        }
    }
    public Set<Course> getCart() {
        return this.cart;
    }
    //Check if course is in the cart
    public Course isCourseInCart(String courseID) {
        for (Course course : cart) {
            if (course.getId().equals(courseID)) {
                return course;
            }
        }
        return null;//Course not found
    }
    public void addToCart(Course course) {
        //Use the facade to add the course to the cart
        studentFacade.addToCart(course, this);
    }
    public void removeFromCart(Course course) {
        //Use the facade to remove the course from the cart
        studentFacade.removeFromCart(course, this);
    }
}
