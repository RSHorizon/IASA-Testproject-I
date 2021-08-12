package Command;

import java.util.Stack;

public class CommandHistory {
    // Author: Robin Steinwarz
    private static CommandHistory instance;
    private Stack<AbstractCommand> commands = new Stack<AbstractCommand>();

    private CommandHistory(){}

    public static synchronized CommandHistory getInstance(){
        if(instance == null){
            instance = new CommandHistory();
        }

        return instance;
    }

    public void addCommand(AbstractCommand command){
        if(command.isUndoable()){
            commands.push(command);
        }
    }

    public AbstractCommand pop(){
        if(!commands.empty()){
            return commands.pop();
        }
        return null;
    }
}
