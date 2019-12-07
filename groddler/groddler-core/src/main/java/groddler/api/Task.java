package groddler.api;

public interface Task {

    void perform(Runnable work);

    void dependsOn(Object taskNotation);
}
