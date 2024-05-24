public abstract class Worker extends User {

    public Worker(String name, String id, String password) {
        super(name, id, password);
    }

    public Course createCourse(Regest_System s, String type, String name, String id, int capacity) {
        //Check if the course is already in the system
        Course c =s.isCourseIDInUse(id);
        if (c != null) {
            System.out.println("This course is already in the system.");
            return c;
        } else {
            Course course = CourseFactory.createCourse(s,this,type, name, id, capacity);
            System.out.println("Course added successfully.");
            return course;
        }
    }
}
