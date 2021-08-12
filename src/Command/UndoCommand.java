package Command;

import Enum.NoteTypeEnum;
import View.NotificationView;

public class UndoCommand extends AbstractCommand {
    // Author: Robin Steinwarz
    @Override
    public void execute(String parameter) {

        AbstractCommand command = CommandHistory.getInstance().pop();

        if(command == null){
            NotificationView.notify(NoteTypeEnum.info, "Nothing to undo");
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
