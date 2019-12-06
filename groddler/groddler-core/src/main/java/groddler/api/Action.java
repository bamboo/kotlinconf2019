package groddler.api;

@SamWithReceiver
public interface Action<T> {

    void execute(T subject);
}

