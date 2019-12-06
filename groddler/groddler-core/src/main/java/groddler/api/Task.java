package groddler.api;

@SamWithReceiver
public interface Task {

    void perform(Runnable work);

    void dependsOn(Object taskNotation);
}
