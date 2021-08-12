package Command;

public interface Command {
    // Author: Robin Steinwarz
    void execute(String parameter);
    void inverse();
    String getHelp();
}
