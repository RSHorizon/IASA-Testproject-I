package Command;

public interface CommandInterface {
    // Author: Robin Steinwarz
    void execute(String parameter);
    void inverse();
    String getHelp();
}
