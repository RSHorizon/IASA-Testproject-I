package Command;

import Enum.NoteType;
import View.NotificationView;

public class UndoCommand extends AbstractCommand {
    // Author: Robin Steinwarz
    @Override
    public void execute(String parameter) {

        AbstractCommand command = CommandHistory.getInstance().pop();

        if(command == null){
            NotificationView.notify(NoteType.info, "Nothing to undo");
        }else{
            command.inverse();
        }
    }

    @Override
    public void inverse() {

    }

    @Override
    public String getHelp() {
        return "undo";
    }
}
