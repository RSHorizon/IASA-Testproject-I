package Command;

import Controller.ConsoleController;

public class ExitCommand extends AbstractCommand {
    // Author: Robin Steinwarz
    @Override
    public void execute(String parameter) {
        ConsoleController.stop = true;
    }

    @Override
    public void inverse() {

    }

    @Override
    public String getHelp() {
        return "exit";
    }
}
