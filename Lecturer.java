
public class Lecturer extends Worker{
    //constructor
    public Lecturer(String name, String id, String password) {
        super(name, id, password);
    }

    @Override
    void printDetails() {
        System.out.println("Lecturer: " + this.getName() + " (ID:" + this.getId() + ")");
    }

}

