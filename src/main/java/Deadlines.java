public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getStatusLine() {
        return String.format("[D]" + super.getStatusLine() + " (by: " + this.deadline + ")");
    }
}
