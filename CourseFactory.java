
class CourseFactory {
    public static Course createCourse(Regest_System s,User owner,String type, String name, String id, int capacity) {
        switch (type) {
            case "Seminar":
                return new Seminar(s,owner,name, id, capacity);
            case "MustCourse":
                return new MustCourse(s,owner,name, id, capacity);
            case "OptionCourse":
                return new OptionCourse(s,owner,name, id, capacity);
            default:
                return null;
        }
    }
}
