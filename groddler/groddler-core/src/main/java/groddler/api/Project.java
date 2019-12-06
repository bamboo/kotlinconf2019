package groddler.api;

public interface Project extends ExtensionAware {

    void task(String name, Action<Task> configuration);
}

