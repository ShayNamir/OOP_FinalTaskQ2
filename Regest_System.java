import java.util.*;

import static java.lang.System.exit;


public class Regest_System implements RegisterableSystem {
    //Capacity of the system
    final static int CAPACITY = 100;

    //This class uses the Singleton design pattern
    private static Regest_System instance = null;

    //Up to 100 users
    private List<User> users;
    private Set<Course> courses; //For avoiding duplicates courses

    //Private constructor
    private Regest_System() {
        this.users = new ArrayList<>();
        this.courses = new HashSet<>();
    }

    //Getters
    public Set<Course> getCourses() {
        //Deep copy of the courses set
        return new HashSet<>(this.courses);
    }
    public static Regest_System getInstance() {
        if (instance == null) {
            instance = new Regest_System();
        }
        return instance;
    }
    public List<User> getUsers() {
        return this.users;
    }
    public User getUserByID(String id) {
        //Check if the ID is in use by a user
        for (User user : this.users) {
            if (user.getId().equals(id))//Compare the IDs
                return user;
        }
        return null;//ID not found
    }

    //Methods
    public User createUser(String type, String name, String ID, String password) {
        //Add the user if their area available spots
        if (users.size() < CAPACITY) {
            //Check if the ID is already in use
            if (getUserByID(ID) != null) {
                System.out.println("This ID is already in use.");
                return null;
            }
            User user = UserFactory.createUser(type, name, ID, password);
            //System.out.println("User created successfully.");
            users.add(user);
            System.out.println(user.getName()+" added successfully.");
            return user;
        }
        //No more spots
            System.out.println("The system has reached its maximum capacity ("+CAPACITY+"). Cannot add more users.");
            return null;
    }
    /*
        This method should return the course
        if the course ID is already in use, otherwise return null
     */
    public Course isCourseIDInUse(String id) {
        //Check if the ID is already in use by a user
        for (Course course : this.courses) {
            if (course.getId().equals(id))//Compare the IDs
                return course;
        }
        return null;
    }
    public Course addCourse(User user, String type,String name ,String id, int capacity) {
        //Check if this user can add a course (only lecturers and Practice can add courses)
        if (user instanceof Lecturer || user instanceof Practice) {
            //Check if the course is already in the system
            Course c =isCourseIDInUse(id);
            if (c != null) {
                System.out.println("This course is already in the system.");
                return c;
            } else {
                Course course = CourseFactory.createCourse(this,user,type, name, id, capacity);
                courses.add(course);
                System.out.println("Course added successfully.");
                return course;
            }
        }
        else {
            System.out.println("Only lecturers and Practice can add courses.");
        }
        return null;//Error
    }

    //User interface
    public void userInterface(){
        System.out.println("********************************************");
        //Simple user interface
        System.out.println("Welcome to the University Registration System");
        System.out.println("Please select an option:");
        System.out.println("1. Register as a new user");
        System.out.println("2. Login as an existing user");
        System.out.println("3. Exit");
        System.out.println("in every stage, type 9 to quit.");

        //User input
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();//assuming the user will enter a valid integer

        //User selection
        switch (option) {
            case 9://Quit
                System.out.println("Thank you for using the University Registration System. Goodbye!");
                return;

            case 1://Register as a new user
                System.out.println("Please enter your name:");
                String name = scanner.next();
                System.out.println("Please enter your user ID:");
                String userId = scanner.next();
                System.out.println("Please enter your password:");
                String password = scanner.next();
                System.out.println("Please select your role:");
                System.out.println("1. Student");
                System.out.println("2. Lecturer");
                System.out.println("3. Practice");
                String role="";
                switch (scanner.nextInt()) {
                    case 9://Quit
                        System.out.println("Thank you for using the University Registration System. Goodbye!");
                        return;
                    case 1://Student
                        role = "Student";
                        break;
                    case 2://Lecturer
                        role = "Lecturer";
                        break;
                    case 3://Practice
                        role = "Practice";
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        userInterface();
                }
                //Check if the user id is already in use
                if (this.getUserByID(userId) != null) {
                    System.out.println("This ID is already in use. Please try again.");
                    userInterface();
                }
                //Registration
                User newUser = this.createUser(role, name, userId, password);
                logedIn(newUser);//User is logged in
                break;

            case 2://Login as an existing user
                System.out.println("Please enter your user ID:");
                String id = scanner.next();
                System.out.println("Please enter your password:");
                String pass = scanner.next();
                User user = this.getUserByID(id);
                if (user == null) {
                    System.out.println("User ID not found. Please try again.");
                    userInterface();
                } else if (!user.getPassword().equals(pass)) {
                    System.out.println("Incorrect password. Please try again.");
                    userInterface();
                } else
                {
                    System.out.println("login successful.");
                    logedIn(user);
                }
                break;

            case 3:
                System.out.println("Thank you for using the University Registration System. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                userInterface();
        }
    }
    private void logedIn(User user){
        //Check the user type
        if (user instanceof Student) {
            Student student = (Student) user;
            optionStudent(student);
        } else// Lecturer or Practice
        {
            optionsStuff((Worker) user);
        }
    }
    private void optionsStuff(Worker worker){
        System.out.println("********************************************");
        System.out.println("Welcome, MR " + worker.getName() + "!");
        System.out.println("Please select an option:");
        System.out.println("1. View your details");
        System.out.println("2. Create a new course");
        System.out.println("3. Edit a course");
        System.out.println("8. Logout");
        System.out.println("in every stage, type 9 to quit.");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 9://Quit
                System.out.println("Thank you for using the University Registration System. Goodbye!");
                exit(0);//Exit the program
            case 1://View your details
                worker.printDetails();
                optionsStuff(worker);
                break;
            case 2://Create a new course
                System.out.println("Please enter the course name:");
                String name = scanner.next();
                System.out.println("Please enter the course ID:");
                String id = scanner.next();
                System.out.println("Please enter the course capacity:");
                int capacity = scanner.nextInt();
                System.out.println("Please select the course type:");
                System.out.println("1. Seminar");
                System.out.println("2. Optional");
                System.out.println("3. Must Course");
                String type="";
                switch (scanner.nextInt()) {
                    case 9://Quit
                        System.out.println("Thank you for using the University Registration System. Goodbye!");
                        exit(0);//Exit the program
                    case 1://Seminar
                        type = "Seminar";
                        break;
                    case 2://Optional
                        type = "OptionCourse";
                        break;
                    case 3://Must Course
                        type = "MustCourse";
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        optionsStuff(worker);
                }
                //Course newCourse = this.addCourse(user, type, name, id, capacity);
                Course newCourse = worker.createCourse(this,type, name, id, capacity);
                if (newCourse != null) {
                    this.courses.add(newCourse);
                    //System.out.println("Course added successfully.");
                }
                optionsStuff(worker);
                break;
            case 3://Edit a course
                editCourse(worker,null);
                break;

            case 8://Logout
                userInterface();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                optionsStuff(worker);
        }
    }
    private void editCourse(Worker worker, Course c){
        //If c in null, the user should enter the course ID
        Course course=c;
        Scanner scanner=new Scanner(System.in);
        if(c==null){
            System.out.println("********************************************");
            System.out.println("Please enter the course ID:");
            String id = scanner.next();
            course = this.isCourseIDInUse(id);
            if (course == null) {
                System.out.println("Course ID not found. Please try again.");
                optionsStuff(worker);
                return;
            }
        }
        //Check if the user is the owner of the course
        if (!course.isOwner(worker)) {
            System.out.println("You are not the owner of this course. Please try again.");
            optionsStuff(worker);
            return;
        }
        System.out.println("Course name: " + course.getName());
        System.out.println("Please select an option:");
        System.out.println("1. Print course details");
        System.out.println("2. Increase course capacity");
        System.out.println("3. Modify course name");
        System.out.println("7. Back to the stuff menu");
        System.out.println("8. Logout");
        System.out.println("in every stage, type 9 to quit.");
        switch (scanner.nextInt()) {
            case 9://Quit
                System.out.println("Thank you for using the University Registration System. Goodbye!");
                exit(0);//Exit the program
            case 1://Print course details
                course.printDetails();
                editCourse(worker,course);
                break;
            case 2://Increase course capacity
                System.out.println("Please enter the number to increase by:");//Assuming the user will enter a valid integer
                int add = scanner.nextInt();
                course.increaseCapacity(add);
                System.out.println("Course capacity updated successfully.");
                optionsStuff(worker);
                break;
            case 3://Modify course name
                System.out.println("Please enter the new name:");
                String name = scanner.next();
                course.setName(name);
                System.out.println("Course:"+course.getName()+" updated successfully.");
                optionsStuff(worker);
                break;
            case 7://Back to the stuff menu
                optionsStuff(worker);
                break;
            case 8://Logout
                userInterface();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                editCourse(worker,course);
        }
    }
    private void optionStudent(Student student){
        System.out.println("********************************************");
        System.out.println("Welcome " + student.getName() + "!");
        System.out.println("Please select an option:");
        System.out.println("1. View your details");
        System.out.println("2. Add a course to your cart");
        System.out.println("3. View courses in your cart");
        System.out.println("4. Remove a course from your cart");
        System.out.println("8. Logout");
        System.out.println("in every stage, type 9 to quit.");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {// Assuming the user will enter a valid integer
            case 9://Quit
                System.out.println("Thank you for using the University Registration System. Goodbye!");
                exit(0);//Exit the program
            case 1://View your details
                student.printDetails();
                optionStudent(student);
                break;
            case 2://Add a course to your cart
                System.out.println("Please enter the course ID:");
                String id = scanner.next();
                Course course = this.isCourseIDInUse(id);
                if (course == null) {
                    System.out.println("Course ID not found. Please try again.");
                    optionStudent(student);
                }
                student.addToCart(course);//Do all checks in the method
                optionStudent(student);
                break;
            case 3://View courses in your cart
                System.out.println("Courses in your cart:");
                for (Course course1 : student.getCart()) {
                    System.out.println("Course ID :"+course1.getId()+", course name: "+course1.getName());
                }
                optionStudent(student);
                break;
            case 4://Remove a course from your cart
                System.out.println("Please enter the course ID:");
                id = scanner.next();
                course = student.isCourseInCart(id);
                if (course == null) {
                    System.out.println("Course ID not found in your cart. Please try again.");
                    optionStudent(student);
                }
                student.removeFromCart(course);//Do all needed in the method
                optionStudent(student);
                break;
            case 8://Logout
                userInterface();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                optionStudent(student);
        }
    }
}
