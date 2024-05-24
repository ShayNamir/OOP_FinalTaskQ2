public class UserFactory {
    public static User createUser(String type, String name, String id, String password) {
        // Factory method to create User objects(Practice, Lecturer and Student)
         switch (type) {
            case "Practice":
                return new Practice (name, id, password);
            case "Lecturer":
                return new Lecturer(name, id, password);
            case "Student":
                return new Student(name, id, password);
            default:
                return null;
        }
    }
}
