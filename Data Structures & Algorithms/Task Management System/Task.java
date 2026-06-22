class Task {

    int taskId;
    String taskName;
    String status;
    // Reference to next task node
    Task next;

    Task(int taskId, String taskName, String status) {

        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    void display() {

        System.out.println(taskId + " " + taskName + " " + status);
    }
}