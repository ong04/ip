public class ToDo extends Task {

    private String deadline;

    public ToDo(String description) {
        super(description);
    }

    public String getStatusLine() {
        return String.format("[T]" + super.getStatusLine());
    }

}
