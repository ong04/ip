public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setIsDone(){
        this.isDone = true;
    }

    public void setNotDone(){
        this.isDone = false;
    }

    public String getDescription(){
        return this.description;
    }

    public String getTask(){
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
