public class Events extends Task {
    private String start;
    private String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStatusLine() {
        return String.format("[E]" +
                super.getStatusLine() +
                " (from: " +
                this.start +
                " to: " +
                this.end + ")");
    }
}
