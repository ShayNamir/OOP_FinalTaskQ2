import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Course {
    private User owner;
    private String name;
    private String id;
    private int capacity;
    private int registered;
    private Set<Student> notificationList;

    // Constructor
    public Course(Regest_System s,User owner, String name, String id, int capacity) {
        this.owner = owner;
        this.name = name;
        this.id = id;
        this.capacity = capacity;
        this.registered = 0;
        this.notificationList = new HashSet<>();
    }
    // Getters
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getCapacity() {
        return this.capacity;
    }
    public int getRegistered() {
        return this.registered;
    }
    public User getOwner() {
        return this.owner;
    }
    public boolean isOwner(User user) {
        return this.owner.equals(user);
    }


    public boolean hasSpace() {
        return this.registered < this.capacity;
    }
    public void register() {
        this.registered++;
    }
    public void unRegistered() {
        this.registered--;
        //Check if there are students in the notification list and notify them
        if(this.registered+1==this.capacity&& !this.notificationList.isEmpty()){
            this.notifyStudents();
        }
    }
    public void increaseRegistered(int add) {
        this.registered += add;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void increaseCapacity(int add) {
        this.capacity += add;
    }
    public void printDetails() {
        System.out.println("Course ID: " + this.id);
        System.out.println("Course Name: " + this.name);
        System.out.println("Course Owner: " + this.owner.getName());
        System.out.println("Course Capacity: " + this.capacity);
        System.out.println("Number of Registered: " + this.registered);
        System.out.println("Students in the notify list: ");
        for (Student student : this.notificationList) {
            System.out.println(student.getName());
        }
    }
    public void addToNotifList(Student student) {
        this.notificationList.add(student);
    }
    //Notify all students in the notification list
    public void notifyStudents() {
        for (Student student : this.notificationList) {
        System.out.println("-------------------");
            System.out.println("Notifaction to " + student.getName() + ": A spot is available in " + this.name + " course.");
        }
        System.out.println("-------------------");
        // Clear the notification list
        this.notificationList.clear();
    }
    // Override equals method (used in set.contains() method)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return (Objects.equals(id, course.id) && Objects.equals(name, course.name));// Compare the id and name of the courses
    }
    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}