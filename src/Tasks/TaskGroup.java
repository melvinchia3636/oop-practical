package Tasks;

public class TaskGroup {
    public String name;
    public TaskInstance[] taskInstances;

    public TaskGroup(String name, TaskInstance[] taskInstances) {
        this.name = name;
        this.taskInstances = taskInstances;
    }
}
