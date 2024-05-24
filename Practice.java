
public class Practice extends Worker {

    //constructor
    public Practice(String name, String id, String password) {
        super(name, id, password);
    }

    @Override
    void printDetails() {
        System.out.println("Practice: " + this.getName() + " (ID:" + this.getId() + ")");
    }

}
