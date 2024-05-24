/*
This interface contains all the functions that's required in this question
 and will implement by Regest_System class
 */
public interface RegisterableSystem {
/*
This function will check if this course can be added and if so it will add it to the courses list in the system
if the course is already in the system it will print a message and return the course as requested
 */
    public Course addCourse(User owner, String type, String name, String id, int capacity);
/*
This function will check if this user can be added and if so it will add it to the users list in the system
 */
public User createUser(String type, String name, String ID, String password);
}
