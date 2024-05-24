import java.util.Objects;

public abstract class User {
    private String id;
    private String name;
    private String password;

    // Constructor
    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
    //Getters
    public String getPassword() {return password;}
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    abstract void printDetails();


    // Override equals method (used in set.contains() method)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return (this.id.equals(user.getId()));// Compare the id is same
    }
    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
