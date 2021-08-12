package Command;

import Enum.NoteType;
import Model.Persistance.ContainerEntity;
import Model.Persistance.ContainerAccess;
import View.NotificationView;

public class StoreCommand extends AbstractCommand {
    // Author: Robin Steinwarz
    @Override
    public void execute(String parameter) {

        if(parameter.trim() != "" && !parameter.contains("\"")){
            NotificationView.notify(NoteType.fail,"No file path was submitted as parameter");
            NotificationView.notify(NoteType.info,"Please use the following format: store \"\'filepath\'\"");
            NotificationView.notify(NoteType.info,"It seems like you don't use \" when using the command");
            return;
        }

        if(parameter.trim() == ""){
            NotificationView.notify(NoteType.fail,"No file path was submitted as parameter");
            NotificationView.notify(NoteType.info,"Please use the following format: store \"\'filepath\'\"");
            return;
        }

        String file = parameter.replace("\""," ");

        ContainerAccess.store(file, ContainerEntity.getContainer());
    }

    @Override
    public void inverse() {

    }

    @Override
    public String getHelp() {
        return "store          \"\'filepath\'\"";
    }
}
